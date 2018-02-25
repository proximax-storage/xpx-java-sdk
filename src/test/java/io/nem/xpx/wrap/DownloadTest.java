package io.nem.xpx.wrap;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import io.nem.xpx.AbstractApiTest;

public class DownloadTest extends AbstractApiTest {

	@Test
	public void lookupPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Download lookup = new Download(remotePeerConnection);
		DownloadData message = lookup.downloadData("199ce1da8b677556aa515d53b213f444c182efccd7240b053682ca7912342c7f",
				this.xPvkey, this.xPubkey);
		try {

			// validate the name.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "1519512452654",
					message.getDataMessage().getName());

			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "This is a test data1",
					new String(message.getData(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lookupPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Download lookup = new Download(remotePeerConnection);
		DownloadData message = lookup.downloadData("559758a9d8e1d7f3810ae7d66deb6c40fb19f8d7971e39d048bb331551b6ef5c",
				this.xPvkey, this.xPubkey);

		try {
			// validate the name.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "small_file_test.txt",
					message.getDataMessage().getName());

			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected",
					"This is a small file for SDK Testing", new String(message.getData(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lookupPlainLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Download lookup = new Download(remotePeerConnection);
		DownloadData message = lookup.downloadData("359f9808b2b535f4816377a0adfef7adc6637dabc2131e7fcb329bbccf437be8",
				this.xPvkey, this.xPubkey);

		try {

			FileUtils.writeByteArrayToFile(new File("src//test//resources//plain_large_"
					+ message.getDataMessage().getName() + System.currentTimeMillis() + ".zip"), message.getData());

			System.out.println(new String(message.getData(), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void lookupSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Download lookup = new Download(remotePeerConnection);
		DownloadData message = lookup.downloadData("d9adcb20cba5db12a9d01ed8b21913ba52280057ca9b69730dbfbc25f9bf70ce",
				this.xPvkey, this.xPubkey);

		try {
			System.out.println(new String(message.getData(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void lookupSecureFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Download lookup = new Download(remotePeerConnection);
		DownloadData message = lookup.downloadData("d505b4f15acb2316072e30ca7bf0db8bee5af4451296ab19403867613221da26",
				this.xPvkey, this.xPubkey);

		try {
			System.out.println(new String(message.getData(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void lookupSecureLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Download lookup = new Download(remotePeerConnection);
		DownloadData message = lookup.downloadData("219384cf4a06cec0ec1bf2ceeb9baf9332d94e920ee76939ab4ffd711a1b9d3f",
				this.xPvkey, this.xPubkey);

		try {

			FileUtils.writeByteArrayToFile(new File("src//test//resources//plain_large_"
					+ message.getDataMessage().getName() + System.currentTimeMillis() + ".zip"), message.getData());

			System.out.println(new String(message.getData(), "UTF-8"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
