package io.nem.xpx.facade.upload;

import java.io.File;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class MultiFileUploadResult {

    private List<FileUploadResult> fileUploadResults;

    public MultiFileUploadResult(List<FileUploadResult> fileUploadResults) {
        this.fileUploadResults = fileUploadResults == null ? emptyList() : unmodifiableList(fileUploadResults);
    }

    public List<FileUploadResult> getFileUploadResults() {
        return fileUploadResults;
    }

    public boolean hasFailure() {
        return fileUploadResults.stream().anyMatch(result -> result.uploadException != null);
    }

    public static class FileUploadResult {

        private File file;
        private UploadResult uploadResult;
        private UploadException uploadException;

        public FileUploadResult(File file, UploadResult uploadResult) {
            this.file = file;
            this.uploadResult = uploadResult;
        }

        public FileUploadResult(File file, UploadException uploadException) {
            this.file = file;
            this.uploadException = uploadException;
        }

        public File getFile() {
            return file;
        }

        public UploadResult getUploadResult() {
            return uploadResult;
        }

        public UploadException getUploadException() {
            return uploadException;
        }
    }

}
