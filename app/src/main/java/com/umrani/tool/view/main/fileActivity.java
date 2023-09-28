package com.umrani.tool.view.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.umrani.tool.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class fileActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 100;

    private ListView filesListView;
    private FileAdapter fileAdapter;
    private List<FileItem> fileList;
    private File currentDirectory;

    private File sourceFile;
    private File destinationFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        filesListView = findViewById(R.id.list_view_files);
        fileList = new ArrayList<>();
        fileAdapter = new FileAdapter(this, fileList);
        filesListView.setAdapter(fileAdapter);

        filesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileItem fileItem = fileList.get(position);
                File file = fileItem.getFile();
                if (file.isDirectory()) {
                    loadFiles(file);
                } else {
                    openFile(file);
                }
            }
        });

        filesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FileItem fileItem = fileList.get(position);
                File file = fileItem.getFile();
                showContextMenu(file);
                return true;
            }
        });

        checkStoragePermission();
    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showPermissionRationaleDialog();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }
        } else {
            loadFiles(Environment.getExternalStorageDirectory());
        }
    }

    private void showPermissionRationaleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required")
                .setMessage("This app requires storage permission to access files.")
                .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(fileActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSION);
                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showPermissionDeniedDialog();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void showPermissionDeniedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Denied")
                .setMessage("You have denied the storage permission. " +
                        "To use this app, go to App Settings and grant the permission.")
                .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openAppSettings();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void loadFiles(File directory) {
        currentDirectory = directory;
        fileList.clear();

        // Load directories
        File[] directories = directory.listFiles(file -> file.isDirectory());
        if (directories != null) {
            for (File dir : directories) {
                fileList.add(new FileItem(dir, true));
            }
        }

        // Load files
        File[] files = directory.listFiles(file -> !file.isDirectory());
        if (files != null) {
            for (File file : files) {
                fileList.add(new FileItem(file, false));
            }
        }

        Collections.sort(fileList);
        fileAdapter.notifyDataSetChanged();
    }

    private void openFile(File file) {
        Uri fileUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String mimeType = getMimeType(file.getAbsolutePath());
        intent.setDataAndType(fileUri, mimeType);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Verify that the intent can be resolved before starting the activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No app available to handle this file type", Toast.LENGTH_SHORT).show();
        }
    }

    private String getMimeType(String url) {
        String extension = url.substring(url.lastIndexOf("."));
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    @Override
    public void onBackPressed() {
        if (currentDirectory != null && !isRootDirectory(currentDirectory)) {
            File parentDirectory = currentDirectory.getParentFile();
            if (parentDirectory != null) {
                loadFiles(parentDirectory);
            }
        } else {
            super.onBackPressed();
        }
    }

    private boolean isRootDirectory(File directory) {
        return directory != null && directory.getAbsolutePath().equals(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void showContextMenu(final File file) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Action")
                .setItems(new CharSequence[]{"Copy", "Move", "Delete", "Rename"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // Copy
                                sourceFile = file;
                                destinationFile = null;
                                Toast.makeText(fileActivity.this, "File copied", Toast.LENGTH_SHORT).show();
                                break;
                            case 1: // Move
                                sourceFile = file;
                                destinationFile = null;
                                Toast.makeText(fileActivity.this, "File moved", Toast.LENGTH_SHORT).show();
                                break;
                            case 2: // Delete
                                deleteFile(file);
                                break;
                            case 3: // Rename
                                showRenameFileDialog(file);
                                break;
                        }
                    }
                })
                .show();
    }

    private void showRenameFileDialog(final File file) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rename File");
        final View view = getLayoutInflater().inflate(R.layout.dialog_rename_file, null);
        builder.setView(view);
        builder.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = ((EditText) view.findViewById(R.id.edit_text_new_name)).getText().toString();
                renameFile(file, newName);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

// TODO: Implement copy, move, delete, and rename functionality
// TODO: MADE BY UMRANI DEVELOPER

    // Copy file
    private void copyFile(File sourceFile, File destinationFile) {
        try {
            FileInputStream inputStream = new FileInputStream(sourceFile);
            FileOutputStream outputStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
            Toast.makeText(this, "File copied successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to copy file", Toast.LENGTH_SHORT).show();
        }
    }

    // Move file
    private void moveFile(File sourceFile, File destinationFile) {
        if (sourceFile.renameTo(destinationFile)) {
            Toast.makeText(this, "File moved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to move file", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete file
    private void deleteFile(File file) {
        if (file.delete()) {
            Toast.makeText(this, "File deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete file", Toast.LENGTH_SHORT).show();
        }
    }

    // Rename file
    private void renameFile(File file, String newName) {
        String parentPath = file.getParent();
        File newFile = new File(parentPath, newName);
        if (file.renameTo(newFile)) {
            Toast.makeText(this, "File renamed successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to rename file", Toast.LENGTH_SHORT).show();
        }
    }


    // TODO: Implement file/directory creation

    // TODO: Implement search functionality

    // TODO: Implement sorting options

    // TODO: Implement file filtering options
}
