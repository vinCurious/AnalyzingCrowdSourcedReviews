/*
 * TestJSONtoCSV2.java
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;

/**
 * This program converts JSON file to customized csv file. This file is
 * responsible for cleaning and preparing the data for classification from
 * User Dataset dataset.
 *
 * @author Vinay Vasant More
 * @author Pratik Kulkarni
 *
 */
public class TestJSONtoCSV2 {
	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String args[]) throws IOException, JSONException {
		int checkinCounter = 0, rowCount = 0;
		//Input from User Dataset
		DataInputStream ins = new DataInputStream(new FileInputStream(
				"/Users/Tyro26/Downloads/yelp_dataset_challenge_academic_dataset/yelp_academic_dataset_user.json"));
		PrintWriter out = new PrintWriter("/Users/Tyro26/Downloads/TestUser4.csv");

		//writing to csv file
		DataInputStream input = new DataInputStream(new FileInputStream(
				"/Users/Tyro26/Downloads/yelp_dataset_challenge_academic_dataset/yelp_academic_dataset_user.json"));

		String str = input.readLine();
		//The columns in the .csv file
		out.println("yelping_since,votes.funny,votes.useful,votes.cool,review_count,name,user_id,fans,average_stars");
		String ys, vf, vu, vc, rc, n, uid, f, as;
		int c = 0;//to store only 65000 lines of data
		for(int i=0;i<100000;i++)
			str=input.readLine();
		while (str != null && c<10000) {
			System.out.println(c++);
			JSONObject jobj = new JSONObject(str);
			ys = jobj.getString("yelping_since");
			JSONObject checkins = jobj.getJSONObject("votes");
			int k1 = checkins.getInt("funny");
			int k2 = checkins.getInt("useful");
			int k3 = checkins.getInt("cool");
			int k4 = jobj.getInt("review_count");
			n = jobj.getString("name");
			uid = jobj.getString("user_id");
			int k5 = jobj.getInt("fans");
			int k6 = jobj.getInt("average_stars");
			out.println(ys + "," + k1 + "," + k2 + "," + k3 + "," + k4 + "," + n + "," + uid + "," + k5 + "," + k6 + ",");
			str = input.readLine();
		}
		
	}

}
