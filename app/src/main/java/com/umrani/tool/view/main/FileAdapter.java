package com.umrani.tool.view.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.umrani.tool.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileAdapter extends ArrayAdapter<FileItem> {

    private static class ViewHolder {
        ImageView fileIconImageView,folderIconImageView;
        TextView fileNameTextView;
    }

    public FileAdapter(Context context, List<FileItem> files) {
        super(context, 0, files);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FileItem fileItem = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_file, parent, false);
            viewHolder.fileIconImageView = convertView.findViewById(R.id.file_icon_image_view);
            viewHolder.fileNameTextView = convertView.findViewById(R.id.file_name_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (fileItem != null) {
            viewHolder.fileNameTextView.setText(fileItem.getDisplayName());
            if (fileItem.isDirectory()) {
                viewHolder.fileIconImageView.setImageResource(R.drawable.ic_folder);
            } else {
                viewHolder.fileIconImageView.setImageResource(R.drawable.ic_file);
            }
        }

        return convertView;
    }




    private String getFileDetails(File file) {
        String details = "";

        // File size
        long fileSize = file.length();
        String fileSizeFormatted = formatFileSize(fileSize);
        details += fileSizeFormatted;

        // File last modified date
        long lastModified = file.lastModified();
        String lastModifiedDate = formatDate(lastModified);
        details += " | " + lastModifiedDate;

        return details;
    }

    private String formatFileSize(long fileSize) {
        final long kiloBytes = 1024;
        final long megaBytes = kiloBytes * 1024;
        final long gigaBytes = megaBytes * 1024;
        final long teraBytes = gigaBytes * 1024;

        if (fileSize < kiloBytes) {
            return fileSize + " B";
        } else if (fileSize < megaBytes) {
            return String.format(Locale.getDefault(), "%.2f KB", fileSize / (float) kiloBytes);
        } else if (fileSize < gigaBytes) {
            return String.format(Locale.getDefault(), "%.2f MB", fileSize / (float) megaBytes);
        } else if (fileSize < teraBytes) {
            return String.format(Locale.getDefault(), "%.2f GB", fileSize / (float) gigaBytes);
        } else {
            return String.format(Locale.getDefault(), "%.2f TB", fileSize / (float) teraBytes);
        }
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        Date date = new Date(timestamp);
        return sdf.format(date);
    }
}
