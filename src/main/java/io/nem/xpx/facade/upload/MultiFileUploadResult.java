package io.nem.xpx.facade.upload;

import java.io.File;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;


/**
 * The Class MultiFileUploadResult.
 */
public class MultiFileUploadResult {

    /** The file upload results. */
    private List<FileUploadResult> fileUploadResults;

    /**
     * Instantiates a new multi file upload result.
     *
     * @param fileUploadResults the file upload results
     */
    public MultiFileUploadResult(List<FileUploadResult> fileUploadResults) {
        this.fileUploadResults = fileUploadResults == null ? emptyList() : unmodifiableList(fileUploadResults);
    }

    /**
     * Gets the file upload results.
     *
     * @return the file upload results
     */
    public List<FileUploadResult> getFileUploadResults() {
        return fileUploadResults;
    }

    /**
     * Checks for failure.
     *
     * @return true, if successful
     */
    public boolean hasFailure() {
        return fileUploadResults.stream().anyMatch(result -> result.uploadException != null);
    }

    /**
     * The Class FileUploadResult.
     */
    public static class FileUploadResult {

        /** The file. */
        private File file;
        
        /** The upload result. */
        private UploadResult uploadResult;
        
        /** The upload exception. */
        private UploadException uploadException;

        /**
         * Instantiates a new file upload result.
         *
         * @param file the file
         * @param uploadResult the upload result
         */
        public FileUploadResult(File file, UploadResult uploadResult) {
            this.file = file;
            this.uploadResult = uploadResult;
        }

        /**
         * Instantiates a new file upload result.
         *
         * @param file the file
         * @param uploadException the upload exception
         */
        public FileUploadResult(File file, UploadException uploadException) {
            this.file = file;
            this.uploadException = uploadException;
        }

        /**
         * Gets the file.
         *
         * @return the file
         */
        public File getFile() {
            return file;
        }

        /**
         * Gets the upload result.
         *
         * @return the upload result
         */
        public UploadResult getUploadResult() {
            return uploadResult;
        }

        /**
         * Gets the upload exception.
         *
         * @return the upload exception
         */
        public UploadException getUploadException() {
            return uploadException;
        }
    }

}
