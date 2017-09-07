package azure_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.Constants;
import com.microsoft.azure.storage.StorageCredentialsAccountAndKey;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobOutputStream;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.SharedAccessBlobPermissions;
import com.microsoft.azure.storage.blob.SharedAccessBlobPolicy;

public class azure_input_test {

	static void azureConnectUpload()
			throws InvalidKeyException, URISyntaxException, StorageException, FileNotFoundException, IOException {

		String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=flexcybersecurity;"
				+ "AccountKey=N+o8GT+MrMwHn0vWH5RfIXnfq/dR+8SBFly7d739s4d4UJw8PUt6qy5KMh1KjUyBOnE+ke2PzyeYBga9cddDdQ==";

		CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
		CloudBlobClient serviceClient = account.createCloudBlobClient();

		CloudBlobContainer container = serviceClient.getContainerReference("flexcybersecuritycontainer");
		container.createIfNotExists();

		CloudBlockBlob blob = container.getBlockBlobReference("lamb_cyber_test.png");
		File sourceFile = new File("/Users/sathya/Desktop/lamb.png");
		blob.upload(new FileInputStream(sourceFile), sourceFile.length());
		System.out.println("File Uploaded");
		// Generating Azure SAS Url
	}

	static void getAzureSASUrl(String blobname) throws URISyntaxException, StorageException, InvalidKeyException,
			ParseException, FileNotFoundException, IOException {

	

		String blob_name = "lamb_flex_test.png";
		
		
		String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=flexcybersecurity;"
				+ "AccountKey=N+o8GT+MrMwHn0vWH5RfIXnfq/dR+8SBFly7d739s4d4UJw8PUt6qy5KMh1KjUyBOnE+ke2PzyeYBga9cddDdQ==";
		CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
		CloudBlobClient serviceClient = account.createCloudBlobClient();
		CloudBlobContainer container_ref = serviceClient.getContainerReference("flexcybersecuritycontainer");
		container_ref.createIfNotExists();

		CloudBlockBlob blob = container_ref.getBlockBlobReference(blob_name);
		File sourceFile = new File("/Users/sathya/Desktop/lamborgini.png");
		blob.upload(new FileInputStream(sourceFile), sourceFile.length());

		// CloudBlobContainer container_reference = container;

		SharedAccessBlobPolicy sasConstraints = new SharedAccessBlobPolicy();

		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		// Specify the current time as the start time for the shared access
		// signature.
		//
		calendar.setTime(new Date());
		// sasConstraints.setSharedAccessStartTime(calendar.getTime());

		// Use the start time delta one hour as the end time for the shared
		// access signature.
		calendar.add(Calendar.HOUR, 10);
		sasConstraints.setSharedAccessExpiryTime(calendar.getTime());

		sasConstraints.setPermissions(EnumSet.of(SharedAccessBlobPermissions.READ, SharedAccessBlobPermissions.LIST));
		String sasBlobToken = blob.generateSharedAccessSignature(sasConstraints,null);

		System.out.println(blob.getUri()+"?"+ sasBlobToken);
	
	}

	// public static void primePublicContainer(CloudBlobClient blobClient,
	// String accountName, String containerName,
	// String blobName, int fileSize) throws Exception {
	//
	// // Create a container if it does not exist. The container name
	// // must be lower case.
	// CloudBlobContainer container =
	// blobClient.getContainerReference(containerName);
	//
	// container.createIfNotExists();
	//
	// // Create a new shared access policy.
	// SharedAccessBlobPolicy sasPolicy = new SharedAccessBlobPolicy();
	//
	// // Set READ and WRITE permissions.
	// //
	// sasPolicy.setPermissions(EnumSet.of(SharedAccessBlobPermissions.READ,
	// SharedAccessBlobPermissions.WRITE,
	// SharedAccessBlobPermissions.LIST, SharedAccessBlobPermissions.DELETE));
	//
	// // Create the container permissions.
	// BlobContainerPermissions containerPermissions = new
	// BlobContainerPermissions();
	//
	// // Turn public access to the container off.
	// containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
	//
	// // Set the policy using the values set above.
	// containerPermissions.getSharedAccessPolicies().put("testwasbpolicy",
	// sasPolicy);
	// container.uploadPermissions(containerPermissions);
	//
	// // Create a blob output stream.
	// CloudBlockBlob blob = container.getBlockBlobReference(blobName);
	// BlobOutputStream outputStream = blob.openOutputStream();
	//
	// outputStream.write(new byte[fileSize]);
	// outputStream.close();
	// }
	public static void main(String[] args) throws InvalidKeyException, FileNotFoundException, URISyntaxException,
			StorageException, IOException, ParseException {
		// azureConnectUpload();

		getAzureSASUrl("sathya_test_sas_url");

	}

}
