package io.nem.xpx.service;

import io.nem.xpx.exceptions.UploadContentFailureException;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;

public class UploadDelegate {

    private UploadApi uploadApi;

    public UploadDelegate(UploadApi uploadApi) {
        this.uploadApi = uploadApi;
    }

    public ResourceHashMessageWrapper uploadBinaryToIpfs(
            byte[] binaryData, String name, String contentType, String keywords, String metadata) {

        try {
            final byte[] resourceHashMessageData = uploadApi.uploadBytesBinary(binaryData, name, contentType, keywords, metadata);
            return new ResourceHashMessageWrapper(resourceHashMessageData);
        } catch (Exception e) {
            throw new UploadContentFailureException("Failed to upload text to ipfs", e);
        }
    }

    public ResourceHashMessageWrapper uploadTextToIpfs (
            byte[] textInBytes, String name, String contentType, String encoding, String keywords, String metadata) {

        try {
            final byte[] resourceHashMessageData = uploadApi.uploadText(textInBytes, name, contentType, encoding, keywords, metadata);
            return new ResourceHashMessageWrapper(resourceHashMessageData);
        } catch (Exception e) {
            throw new UploadContentFailureException("Failed to upload text to ipfs", e);
        }
    }

    public ResourceHashMessageWrapper uploadPathToIpfs(String path, String name, String keywords, String metadata) {

        try {
            final byte[] resourceHashMessageData = uploadApi.uploadPath(path, name, keywords, metadata);
            return new ResourceHashMessageWrapper(resourceHashMessageData);
        } catch (Exception e) {
            throw new UploadContentFailureException("Failed to upload text to ipfs", e);
        }
    }


    public static class ResourceHashMessageWrapper {

        private final byte[] data;

        public ResourceHashMessageWrapper(byte[] data) {
            this.data = data;
        }

        public byte[] getData() {
            return data;
        }

        public ResourceHashMessage toResourceHashMessage() {
            return ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(data)));
        }
    }
}
