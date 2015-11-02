package com.alchemy.api;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AlchemyKeywordApi {

	public void saveKeywords(String JsonResponse, String filePath) {
		try {

			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file, true);
			DataOutputStream dos = new DataOutputStream(fos);
			// System.out.println(System.getProperty("user.dir"));

			// if file doesnt exists, then create it
			System.out.println(file.getAbsolutePath());
			if (!file.exists()) {
				file.createNewFile();
			}

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(JsonResponse);
			// getting the keywords.
			JSONArray jsonArray = (JSONArray) jsonObject
					.get(AlchemyApiConstants.JSON_KEY_KEYWORDS);
			Iterator iter = jsonArray.iterator();

			while (iter.hasNext()) {
				JSONObject keyword = (JSONObject) iter.next();
				String relevance = (String) keyword.get(AlchemyApiConstants.JSON_KEY_RELEVANCE);
				float relevanceFloat = Float.parseFloat(relevance);
				JSONObject sentimentObject = (JSONObject) keyword
						.get(AlchemyApiConstants.JSON_KEY_SENTIMENT);
				String score = (String)sentimentObject.get(AlchemyApiConstants.JSON_KEY_SCORE);
				float scoreFloat = Float.parseFloat(score);
				// getting the text only when the sentiment type is positive.
				if (AlchemyApiConstants.SENTIMENT_TYPE_POSTIVE
						.equals(sentimentObject
								.get(AlchemyApiConstants.JSON_KEY_SENTIMENT_TYPE))) {
					if (scoreFloat >= 0.5 && relevanceFloat >= 0.5) {
					dos.writeChars((String) keyword
							.get(AlchemyApiConstants.JSON_KEY_TEXT));
					dos.writeChars("\n");
					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}