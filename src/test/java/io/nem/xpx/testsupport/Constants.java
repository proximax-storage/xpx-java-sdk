package io.nem.xpx.testsupport;

import io.nem.xpx.utils.JsonUtils;

import java.io.File;

import static java.util.Collections.singletonMap;

public class Constants {

    public static final File SAMPLE_PDF_FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");
    public static final File SAMPLE_PDF_FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");
    public static final File SAMPLE_NON_EXISTENT_FILE = new File("src//test//resources//pdf_non_existent.pdf");
    public static final String SAMPLE_ZIP_FILE_NAME = "test.zip";
    public static final String SAMPLE_KEYWORDS = "plain,file";
    public static final String SAMPLE_METADATA = JsonUtils.toJson(singletonMap("key1", "value1"));

    // testnet keys
    public static final String TEST_PRIVATE_KEY = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";

    //public static final String TEST_PUBLIC_KEY = "092f13a06496c002510a6afc03f5db522664716aaeefdded450106df1624dd3d";
    public static final String TEST_PUBLIC_KEY = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";

    public static final String TEST_UNKNOWN_KEY = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc86";
}
