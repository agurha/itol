/*
    Copyright (c) 2015 Wolfgang Imig
    
    This file is part of the library "JOA Issue Tracker for Microsoft Outlook".

    This file must be used according to the terms of   
      
      MIT License, http://opensource.org/licenses/MIT

 */
package com.wilutions.itol;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

import javax.swing.filechooser.FileSystemView;

public class FileListViewHandler {

	public static void apply(ListView<File> listView) {

		listView.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
			@Override
			public ListCell<File> call(ListView<File> list) {
				return new AttachmentListCell();
			}
		});

		listView.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasFiles()) {
					event.acceptTransferModes(TransferMode.COPY);
				} else {
					event.consume();
				}
			}
		});

		// Dropping over surface
		listView.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasFiles()) {
					success = true;
					for (File file : db.getFiles()) {
						listView.getItems().add(file);
					}
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

	private static class AttachmentListCell extends ListCell<File> {
		@Override
		public void updateItem(File item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setGraphic(null);
				setText(null);
			} else {

				Image fxImage = getFileIcon(item);
				if (fxImage != null) {
					ImageView imageView = new ImageView(fxImage);
					setGraphic(imageView);
				}

				setText(item.getName());
			}
		}
	}

	static HashMap<String, Image> mapOfFileExtToSmallIcon = new HashMap<String, Image>();

	private static String getFileExt(String fname) {
		String ext = ".";
		int p = fname.lastIndexOf('.');
		if (p >= 0) {
			ext = fname.substring(p);
		}
		return ext.toLowerCase();
	}

	private static javax.swing.Icon getJSwingIconFromFileSystem(File file) {

		// Windows {
		FileSystemView view = FileSystemView.getFileSystemView();
		javax.swing.Icon icon = view.getSystemIcon(file);
		// }

		// OS X {
		// final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
		// javax.swing.Icon icon = fc.getUI().getFileView(fc).getIcon(file);
		// }

		return icon;
	}

	private static Image getFileIcon(File file) {
		final String ext = getFileExt(file.getName());

		Image fileIcon = mapOfFileExtToSmallIcon.get(ext);
		if (fileIcon == null) {

			javax.swing.Icon jswingIcon = null;

			if (file.exists()) {
				jswingIcon = getJSwingIconFromFileSystem(file);
			} else {
				File tempFile = null;
				try {
					tempFile = File.createTempFile("icon", ext);
					jswingIcon = getJSwingIconFromFileSystem(tempFile);
				} catch (IOException ignored) {
					// Cannot create temporary file.
				} finally {
					if (tempFile != null) {
						tempFile.delete();
					}
				}
			}

			if (jswingIcon != null) {
				fileIcon = jswingIconToImage(jswingIcon);
				mapOfFileExtToSmallIcon.put(ext, fileIcon);
			}
		}

		return fileIcon;
	}

	private static Image jswingIconToImage(javax.swing.Icon jswingIcon) {
		BufferedImage bufferedImage = new BufferedImage(jswingIcon.getIconWidth(), jswingIcon.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);
		jswingIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
		return SwingFXUtils.toFXImage(bufferedImage, null);
	}
}
