package com.mplatform.framework.base.initializer;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
//import com.couchbase.client.protocol.views.ComplexKey;
import rx.functions.Func1;

import java.io.File;
import java.util.*;


public class CouchbaseClient {
	
	
	CouchbaseCluster cluster2b = null;
	JsonTranscoder trans = new JsonTranscoder();
	
	
		
		
	public String openCouchbaseConnection(String connectionStr)
	{
			
		
		try
		{
		 cluster2b = CouchbaseCluster.create(connectionStr);
		 return "SUCCESS";
		}catch(Exception e)
		{
			System.out.println("Excetion while starting Couchbase Connection");
			e.printStackTrace();
			return "ERROR";
			//System.exit(1);
		}	 	
	      
		
	}
	
		
	public void closeCouchbaseConnection(){
		
		System.out.println("Disconnecting Couchbase Session");
		try{
		cluster2b.disconnect();
		}catch(Exception e)
		{
			System.out.println("Exception while closing the coucbhase session");
			e.printStackTrace();
		}
		
	}
	
	
	public String readCouchbase(String bucketName,String jsonDocument){
		

		Bucket bucket = null;
			
		JsonDocument result = null;
		
		String jsonDocContent = null;
		
						
		try{
			bucket = cluster2b.openBucket(bucketName);
			result = bucket.get(jsonDocument);
			jsonDocContent = result.content().toString();



		}catch(Exception e){
			System.out.println( "Exception on reading document:"+jsonDocument+" with error:"+ e.toString());
			//System.exit(1);
			return null;

		}
		

			
		return jsonDocContent;
	}


	public String readCouchbase(String bucketName,String jsonDocument,String strLocation,String envStr){

		boolean errorStatus = false;

		Bucket bucket = null;

		JsonDocument result = null;

		String jsonDocContent = null;


		try{
			bucket = cluster2b.openBucket(bucketName);
			result = bucket.get(jsonDocument);
			jsonDocContent = result.content().toString();
		}catch(Exception e){
			System.out.println( "Exception on reading document:"+jsonDocument+" with error:"+ e.toString());
			//System.exit(1);
			//return null;

			errorStatus = true;
		}

		if(jsonDocContent == null){
			System.out.println( "Exception on reading document"+jsonDocument);
			System.exit(1);
		}


		//bucket.close();

		//System.out.println(jsonDocContent);

		return jsonDocContent;
	}


	public ArrayList<HashMap<String,Object>> retrieveCouchbaseView(String bucketName,String desingDocument,String viewName){


		Bucket bucket = cluster2b.openBucket(bucketName);
		ArrayList<HashMap<String,Object>> resultArrayList = new ArrayList<HashMap<String,Object>>();

		HashMap<String,Object> dataMap = null;

		// Perform the ViewQuery
		//ViewResult result = bucket.query((ViewQuery.from(desingDocument, viewName).development(true)));
		ViewResult result = bucket.query((ViewQuery.from(desingDocument, viewName)));


		Iterator<ViewRow> rowIterator = result.rows();

		System.out.println(result.totalRows());

		while (rowIterator.hasNext()) {
			ViewRow row = rowIterator.next();

			dataMap = new HashMap<String,Object>();
			dataMap.put("content",row.document().content());
			dataMap.put("expiration",row.key());

		/*	JsonDocument doc = row.document();
			System.out.println(row.key());*/
			resultArrayList.add(dataMap);

		}

		return 	resultArrayList;

	}


	public ViewResult readCouchbaseView(String bucketName,String desingDocument,String viewName){


		Bucket bucket = cluster2b.openBucket(bucketName);

		// Perform the ViewQuery
		ViewResult result = bucket.query((ViewQuery.from(desingDocument, viewName)));

		return 	result;

	}


	public void checkBulkCouchbaseDocument(String bucketName,ArrayList<HashMap<String,Object>> arrayList){
		
		Bucket bucket = cluster2b.openBucket(bucketName);
		
		HashMap<String,Object> temp = new HashMap<String,Object>();
		ArrayList<String> nulPointerDocuments = new ArrayList<String>();
		
		JsonDocument result = null;
		String jsonDocContent = null;
		String document = null;
		
		
				for(int i=0;i<arrayList.size();i++){	
					
					temp = arrayList.get(i);
					
					for (int j=0;j<temp.size();j++){
						
						document = "Model."+temp.get("ID").toString();
						
						try{	
							result = bucket.get("Model."+temp.get("ID").toString());
							
							
						}catch(NullPointerException e){
							
							nulPointerDocuments.add(document);
							System.out.println(document);
							e.printStackTrace();
						}
						catch(Exception e){
							
							System.out.println(document);
							e.printStackTrace();
						}
						
						if(result == null){
							nulPointerDocuments.add(document);
							System.out.println("Document has null string : "+document);
						}
					
						/*try{
						 jsonDocContent = result.content().toString();
						}catch(NullPointerException e){
							System.out.println(document);
							nulPointerDocuments.add(document);
							System.out.println(document);
							e.printStackTrace();
						}
						*/
						
				}
		
		
				}
				
				System.out.println("NullPointer Exception Documents");
				
				for(String str : nulPointerDocuments){
					System.out.println(str);
					System.out.println();
				}
		
	}

	

	/**
	 * Function - Update Single JSON Document.

	 * @return Boolean
	 */
	
	public Boolean updateDocument(String documentKey,String bucketName,String jsonDocString){
		
		
		try{
				Bucket bucket = cluster2b.openBucket(bucketName);
				
				//JsonObject content = JsonObject.create().put("", jsonDocString);
				
				JsonObject  jsonObject = null;
				
				
				try {
					jsonObject = trans.stringToJsonObject(jsonDocString);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				JsonDocument doc = JsonDocument.create(documentKey,jsonObject);
				
				bucket.replace(doc);
				
										
				JsonDocument result = bucket.get(documentKey);
				
				String jsonDocContent = result.content().toString();
				
				//bucket.close();
				
				//System.out.println(jsonDocContent);
				
				if(jsonDocContent.isEmpty()){
					//System.out.println("No documenbt");
					return false;
				}
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
		}
			
		return true;
		
		
		
		
	}
	
	
	/**
	 * Function - Create Single JSONDocument.

	 * @return
	 */
	
	public JsonDocument createJsonDocuments(String key,JsonObject jsonDocString){
		
				
		JsonDocument doc = JsonDocument.create(key,jsonDocString);
		
		return doc;
			
	}
	
	

	public ArrayList<String> checkVAMDocumentExists(int accountID,ArrayList<HashMap<String, Object>> dbResultSet,String bucketName){
		
		ArrayList<String> resultList = new ArrayList<String>();
		Iterator<String> Itr = null;
		
		try{
				Bucket bucket = cluster2b.openBucket(bucketName);
				
				//JsonObject content = JsonObject.create().put("", jsonDocString);
				
				for(int j=0;j<dbResultSet.size();j++){
					
					HashMap<String,Object> list = dbResultSet.get(j);
			    	
			    	Itr = list.keySet().iterator();
			    	
			    	while(Itr.hasNext())
			    	{
			    		String key = Itr.next().toString();
			    		String docKey = list.get(key).toString();
			    		JsonDocument result = bucket.get("VAMData."+accountID+"."+docKey);
			    		
			    		if(result == null){
			    			resultList.add(docKey);
			    		}
			    		
			    	}
				
				}			
				
				
		}catch(Exception e){
			
			e.printStackTrace();
			//return false;
		}
			
		return resultList;
	}


	/**
	 * Function - Read Couchbase Document Expire date.
	 */

	public Integer readCouchbaseExpire(String bucketName,String jsonDocument){

		Bucket bucket = cluster2b.openBucket(bucketName);

		JsonDocument result = bucket.get(jsonDocument);

		Integer jsonDocContent = 0;

		try{
			jsonDocContent = result.expiry();
		}catch(Exception e){
			//return "Exception on reading document:"+jsonDocument+" with error:"+ e.toString();
			System.out.println("Exception on reading document");
			System.exit(1);
		}

		return jsonDocContent;
	}


//	public Integer queryViewByKey(String bucketName,String visitorID,Integer accountID){
//
//
//		Bucket bucket = cluster2b.openBucket(bucketName);
//
//		//Query query = new Query();
//
//		ComplexKey startKey = ComplexKey.of(visitorID, accountID);
//
//	/*	ViewResult result = bucket.query((ViewQuery.from(desingDocument, viewName)));
//
//		JsonDocument result = bucket.get(jsonDocument);*/
//
//		Integer jsonDocContent = 0;
//
//
//
//		return jsonDocContent;
//	}


	}

