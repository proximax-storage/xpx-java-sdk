package io.nem.xpx.testsupport;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.utils.JsonUtils;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.Collections.singletonMap;


/**
 * The Class Constants.
 */
public class Constants {

    public static final Logger LOGGER = Logger.getAnonymousLogger();

    /**
     * Instantiates a new constants.
     */
    private Constants() {}

    /** The Constant REMOTE_PEER_CONNECTION. */
    public static final RemotePeerConnection REMOTE_PEER_CONNECTION = getDevRemotePeerConnection();

    /** The Constant LOCAL_HTTP_PEER_CONNECTION. */
    public static final LocalHttpPeerConnection LOCAL_HTTP_PEER_CONNECTION = getDevLocalHttpPeerConnection();

    /** The Constant PDF_FILE1. */
    public static final File PDF_FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");

    /** The Constant PDF_FILE2. */
    public static final File PDF_FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");

    /** The Constant NON_EXISTENT_FILE. */
    public static final File NON_EXISTENT_FILE = new File("src//test//resources//pdf_non_existent.pdf");

    /** The Constant LARGE_FILE. */
    public static final File LARGE_FILE = new File("src//test//resources//test_large_file.zip");


    /** The Constant ZIP_FILE. */
    public static final File ZIP_FILE = new File("src//test//resources//zip_file.zip");

    /** The Constant LARGE_VIDEO_MP4_FILE. */
    public static final File LARGE_VIDEO_MP4_FILE = new File("src//test//resources//test_large_video.mp4");

    /** The Constant SMALL_VIDEO_MOV_FILE. */
    public static final File SMALL_VIDEO_MOV_FILE = new File("src//test//resources//test_file.mov");

    /** The Constant SMALL_FILE. */
    public static final File SMALL_FILE = new File("src//test//resources//test_small_file.txt");

    /** The Constant HTML_FILE. */
    public static final File HTML_FILE = new File("src//test//resources//test_html.html");

    /** The Constant MOSAIC_LAND_REGISTRY. */
    public static final Mosaic MOSAIC_LAND_REGISTRY =
            new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"), Quantity.fromValue(0));

    /** The Constant MOSAIC_PRX. */
    public static final Mosaic MOSAIC_PRX =
            new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(0));

    /** The Constant METADATA_AS_MAP. */
    public static final Map<String, String> METADATA_AS_MAP = singletonMap("key1", "value1");

    /** The Constant METADATA_AS_STRING. */
    public static final String METADATA_AS_STRING = JsonUtils.toJson(METADATA_AS_MAP);

    /** The Constant TEST_PRIVATE_KEY. */
    // testnet keys - first pair
    public static final String TEST_PRIVATE_KEY = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";

    /** The Constant TEST_PUBLIC_KEY. */
    public static final String TEST_PUBLIC_KEY = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";

    /** The Constant TEST_PRIVATE_KEY_KEYPAIR. */
    public static final KeyPair TEST_PRIVATE_KEY_KEYPAIR = new KeyPair(PrivateKey.fromHexString(TEST_PRIVATE_KEY));

    /** The Constant TEST_PUBLIC_KEY_KEYPAIR. */
    public static final KeyPair TEST_PUBLIC_KEY_KEYPAIR = new KeyPair(PublicKey.fromHexString(TEST_PUBLIC_KEY));

    /** The Constant TEST_PRIVATE_KEY_ACCOUNT. */
    public static final Account TEST_PRIVATE_KEY_ACCOUNT = new Account(TEST_PRIVATE_KEY_KEYPAIR);

    /** The Constant TEST_PUBLIC_KEY_ACCOUNT. */
    public static final Account TEST_PUBLIC_KEY_ACCOUNT = new Account(TEST_PUBLIC_KEY_KEYPAIR);

    /** The Constant TEST_PRIVATE_KEY_2. */
    // testnet keys - second pair
    public static final String TEST_PRIVATE_KEY_2 = "545d88f0bd4bb088bda629f09df13acebff9e260330a1ff19a1bcfbc07c7c3d8";

    /** The Constant TEST_PUBLIC_KEY_2. */
    public static final String TEST_PUBLIC_KEY_2 = "5475e1fa61155dbec62e517917e5cf7e6ad642b27781921d3e0d4f514ea607f6";

    /** The Constant TEST_PRIVATE_KEY_3. */
    // testnet keys - third pair
    public static final String TEST_PRIVATE_KEY_3 = "e079b5587fd841d3ea22b4a7be0ec362f01fc47cb26a8b6146882ab9b5953219";

    /** The Constant TEST_PUBLIC_KEY_3. */
    public static final String TEST_PUBLIC_KEY_3 = "0a18107148e5e4b2e7eed844e06c7051885eeebb39cc8fcb139edc0380f3d219";


    /** The Constant MIJIN_PRIVATE_KEY. */
    // privatenet/mijin keys - first pair
    public static final String MIJIN_PRIVATE_KEY = "90951d4f876e3a15b8507532a051857e933a87269bc0da7400d1604bedc93aec";

    /** The Constant MIJIN_PUBLIC_KEY_2. */
    public static final String MIJIN_PUBLIC_KEY = "d72ebcdf243f9d217f448d937ac9431ba6ee4984b167015abcfc5b22e169ca23";

    /** The Constant SECURE_PASSWORD. */
    public static final String SECURE_PASSWORD	= "hkcymenwcxpzkoyowuagcuhvrhavtdcrxbfqganecoxuirxekq";

    /** The Constant FILE_TO_PLAIN_MSG_NEM_HASH_MAP. */
    public static final Map<File, String> FILE_TO_PLAIN_MSG_NEM_HASH_MAP = fileToPlainMessageNemHashMap();

    /** The Constant FILE_TO_SECURE_MSG_NEM_HASH_MAP. */
    public static final Map<File, String> FILE_TO_SECURE_MSG_NEM_HASH_MAP = fileToSecureMessageNemHashMap();

    /**
     * File to plain message nem hash map.
     *
     * @return the map
     */
    private static Map<File, String> fileToPlainMessageNemHashMap() {
        final HashMap<File, String> map = new HashMap<>();
        map.put(PDF_FILE1, "90b901d3fb1cbcac16e93b1033131b3795aa0b821ae18a622d51ae7da4807287");
        map.put(PDF_FILE2, "b086343531be6d2ae2fe17ce9188f55ee2ccd4466d8c161bfe5858ae5a6c017a");
        map.put(LARGE_VIDEO_MP4_FILE, "ce6f43c3f0c95c96f904a3e97891228c647cb4f7af8628d82cf7eb35aa1a7035");
        map.put(SMALL_FILE, "441a9aad87716bf56f27a1e2adc614a7bcd8077bfa1a7057e662eb14d5615fff");
        return map;
    }

    /**
     * File to secure message nem hash map.
     *
     * @return the map
     */
    private static Map<File, String> fileToSecureMessageNemHashMap() {
        final HashMap<File, String> map = new HashMap<>();
        map.put(PDF_FILE1, "c450a78b947156748d2e89d890f28f2662f61f11161b2d4aa67ee7dbb3b89096");
//        map.put(PDF_FILE2, "436cdb30018d3e23cd016fbde04a80d5747fccac7e8a97f7448afcfe09e600d7");
        map.put(SMALL_FILE, "a943b44ab9f29ad9254a77b5ef4c1dd7b57feb83e2c8e7535b0d32b5a6983e40");
        return map;
    }

    /**
     * Gets the dev remote peer connection.
     *
     * @return the dev remote peer connection
     */
    private static RemotePeerConnection getDevRemotePeerConnection() {
        try {
            return new RemotePeerConnection("http://dev-gateway.internal.proximax.io:8881");
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the dev local http peer connection.
     *
     * @return the dev local http peer connection
     */
    private static LocalHttpPeerConnection getDevLocalHttpPeerConnection() {
        try {
            return new LocalHttpPeerConnection(
                    ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
                    ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
            );
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe(e.getMessage());
            return null;
        }
    }


}
