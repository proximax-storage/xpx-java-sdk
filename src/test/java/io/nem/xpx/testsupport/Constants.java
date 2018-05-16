package io.nem.xpx.testsupport;

import io.nem.xpx.utils.JsonUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

public class Constants {

    public static final File SAMPLE_PDF_FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");
    public static final File SAMPLE_PDF_FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");
    public static final File SAMPLE_NON_EXISTENT_FILE = new File("src//test//resources//pdf_non_existent.pdf");
    public static final File SAMPLE_LARGE_FILE = new File("src//test//resources//test_large_file.zip");
    public static final File SAMPLE_LARGE_VIDEO_FILE = new File("src//test//resources//test_large_video.mp4");
    public static final File SAMPLE_SMALL_FILE = new File("src//test//resources//test_small_file.txt");
    public static final String SAMPLE_ZIP_FILE_NAME = "test.zip";
    public static final String SAMPLE_KEYWORDS = "plain,file";
    public static final String SAMPLE_METADATA = JsonUtils.toJson(singletonMap("key1", "value1"));

    // testnet keys - first pair
    public static final String TEST_PRIVATE_KEY = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";
    public static final String TEST_PUBLIC_KEY = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";
    //public static final String TEST_PUBLIC_KEY = "092f13a06496c002510a6afc03f5db522664716aaeefdded450106df1624dd3d";

    // testnet keys - second pair
    public static final String TEST_PRIVATE_KEY_2 = "545d88f0bd4bb088bda629f09df13acebff9e260330a1ff19a1bcfbc07c7c3d8";
    public static final String TEST_PUBLIC_KEY_2 = "5475e1fa61155dbec62e517917e5cf7e6ad642b27781921d3e0d4f514ea607f6";

    // testnet keys - third pair
    public static final String TEST_PRIVATE_KEY_3 = "e079b5587fd841d3ea22b4a7be0ec362f01fc47cb26a8b6146882ab9b5953219";
    public static final String TEST_PUBLIC_KEY_3 = "0a18107148e5e4b2e7eed844e06c7051885eeebb39cc8fcb139edc0380f3d219";

    public static final Map<File, String> FILE_TO_PLAIN_NEM_HASH_MAP = fileToNemHashMap();

    private static Map<File, String> fileToNemHashMap() {
        final HashMap<File, String> map = new HashMap<>();
        map.put(SAMPLE_PDF_FILE1, "e0ca6d958ba01592ddeaa40e9d810a4314707f6673c2271e5d0eeb018a4be997");
        map.put(SAMPLE_LARGE_VIDEO_FILE, "ce6f43c3f0c95c96f904a3e97891228c647cb4f7af8628d82cf7eb35aa1a7035");
//        map.put(SAMPLE_LARGE_FILE, "1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");
//        map.put(SAMPLE_SMALL_FILE, "e0ca6d958ba01592ddeaa40e9d810a4314707f6673c2271e5d0eeb018a4be997");
        return map;
    }

}
