package io.nem.xpx.testsupport;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.utils.JsonUtils;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

public class Constants {

    private Constants() {}

    public static final RemotePeerConnection REMOTE_PEER_CONNECTION = getDevRemotePeerConnection();

    public static final LocalHttpPeerConnection LOCAL_HTTP_PEER_CONNECTION = getDevLocalHttpPeerConnection();

    public static final File PDF_FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");
    public static final File PDF_FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");
    public static final File NON_EXISTENT_FILE = new File("src//test//resources//pdf_non_existent.pdf");
    public static final File LARGE_FILE = new File("src//test//resources//test_large_file.zip");
    public static final File LARGE_VIDEO_MP4_FILE = new File("src//test//resources//test_large_video.mp4");
    public static final File SMALL_VIDEO_MOV_FILE = new File("src//test//resources//test_file.mov");
    public static final File SMALL_FILE = new File("src//test//resources//test_small_file.txt");
    public static final File HTML_FILE = new File("src//test//resources//test_html.html");

    public static final Mosaic MOSAIC_LAND_REGISTRY =
            new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"), Quantity.fromValue(0));
    public static final Mosaic MOSAIC_PRX =
            new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(0));

    public static final Map<String, String> METADATA_AS_MAP = singletonMap("key1", "value1");
    public static final String METADATA_AS_STRING = JsonUtils.toJson(METADATA_AS_MAP);

    // testnet keys - first pair
    public static final String TEST_PRIVATE_KEY = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";
    public static final String TEST_PUBLIC_KEY = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";

    // testnet keys - second pair
    public static final String TEST_PRIVATE_KEY_2 = "545d88f0bd4bb088bda629f09df13acebff9e260330a1ff19a1bcfbc07c7c3d8";
    public static final String TEST_PUBLIC_KEY_2 = "5475e1fa61155dbec62e517917e5cf7e6ad642b27781921d3e0d4f514ea607f6";

    // testnet keys - third pair
    public static final String TEST_PRIVATE_KEY_3 = "e079b5587fd841d3ea22b4a7be0ec362f01fc47cb26a8b6146882ab9b5953219";
    public static final String TEST_PUBLIC_KEY_3 = "0a18107148e5e4b2e7eed844e06c7051885eeebb39cc8fcb139edc0380f3d219";

    public static final Map<File, String> FILE_TO_PLAIN_MSG_NEM_HASH_MAP = fileToPlainMessageNemHashMap();
    public static final Map<File, String> FILE_TO_SECURE_MSG_NEM_HASH_MAP = fileToSecureMessageNemHashMap();

    private static Map<File, String> fileToPlainMessageNemHashMap() {
        final HashMap<File, String> map = new HashMap<>();
        map.put(PDF_FILE1, "90b901d3fb1cbcac16e93b1033131b3795aa0b821ae18a622d51ae7da4807287");
        map.put(PDF_FILE2, "b086343531be6d2ae2fe17ce9188f55ee2ccd4466d8c161bfe5858ae5a6c017a");
        map.put(LARGE_VIDEO_MP4_FILE, "ce6f43c3f0c95c96f904a3e97891228c647cb4f7af8628d82cf7eb35aa1a7035");
        map.put(SMALL_FILE, "441a9aad87716bf56f27a1e2adc614a7bcd8077bfa1a7057e662eb14d5615fff");
        return map;
    }

    private static Map<File, String> fileToSecureMessageNemHashMap() {
        final HashMap<File, String> map = new HashMap<>();
        map.put(PDF_FILE1, "4315c8619422407f5e2b82788429214166307930747f69a2cfb7b6fba420635d");
        map.put(PDF_FILE2, "436cdb30018d3e23cd016fbde04a80d5747fccac7e8a97f7448afcfe09e600d7");
        map.put(SMALL_FILE, "f854b60a585a83cf2484bd254380e9bb8240f999cbd78eeb8e9b8792aed4025a");
        return map;
    }

    private static RemotePeerConnection getDevRemotePeerConnection() {
        try {
            return new RemotePeerConnection("https://dev.gateway.proximax.io");
        } catch (Exception e) {
            return null;
        }
    }

    private static LocalHttpPeerConnection getDevLocalHttpPeerConnection() {
        try {
            return new LocalHttpPeerConnection(
                    ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
                    ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
            );
        } catch (Exception e) {
            return null;
        }
    }


}
