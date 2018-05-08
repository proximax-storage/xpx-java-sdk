package io.nem.xpx.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class UploadMultiFilesParameter extends DataParameter implements Serializable {

	private List<File> files = new ArrayList<>();

	public List<File> getFiles() {
		return files;
	}

	public void addFiles(List<File> files) {
		this.files.addAll(files);
	}

	public void addFiles(File... files) {
		this.files.addAll(asList(files));
	}

	public void addFile(File file) {
		this.files.add(file);
	}
}
