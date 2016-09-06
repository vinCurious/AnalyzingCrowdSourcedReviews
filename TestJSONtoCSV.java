
/* 
 * TestJSONtoCSV.java 
 *  
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This program converts JSON file to customized csv file. This file is
 * responsible for cleaning and preparing the data for classification from
 * business and checkin dataset.
 *
 * @author Vinay Vasant More
 * @author Pratik Kulkarni
 *
 */
public class TestJSONtoCSV {

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String args[]) throws IOException, JSONException {
		int checkinCounter = 0, rowCount = 0;
		int checkinCount = 0;

		// Input from Business dataset
		DataInputStream businessInput = new DataInputStream(
				new FileInputStream("C:\\Users\\vin\\Desktop\\yelp_academic_dataset_business.json"));
		// Input from Checkin dataset
		DataInputStream checkinInput = new DataInputStream(
				new FileInputStream("C:\\Users\\vin\\Desktop\\yelp_academic_dataset_checkin.json"));
		// Writing into csv file
		PrintWriter out = new PrintWriter("C:\\Users\\vin\\Desktop\\big data final\\TestBusiness.csv");
		HashMap<String, Integer> data = new HashMap<>();

		// writing header line of csv file
		out.println("id,name,category,stars,review_count,checkins");

		// adding checkin count for particular business from checkin dataset
		String str = checkinInput.readLine(), b_id, tmp_keys[];
		while (str != null) {
			checkinCount = 0;
			JSONObject jobj = new JSONObject(str);
			b_id = jobj.getString("business_id");
			JSONObject checkbusinessInput = jobj.getJSONObject("checkin_info");
			tmp_keys = JSONObject.getNames(checkbusinessInput);
			for (String key : tmp_keys) {
				checkinCount += checkbusinessInput.getInt(key);
			}
			data.put(b_id, checkinCount);
			str = checkinInput.readLine();
		}

		for (int i = 0; i < 77445; i++) {
			String str1 = businessInput.readLine();
			JSONObject object = new JSONObject(str1);
			String id = object.getString("business_id");
			if (data.containsKey(id)) {
				checkinCounter = data.get(id);
			} else
				checkinCounter = 0;
			String name = object.getString("name");

			if (name.contains(","))
				name = name.replace(",", " ");

			JSONArray arr = object.getJSONArray("categories");
			double stars = object.getDouble("stars");
			int revCount = object.getInt("review_count");
			String tmp = arr.length() > 0 ? arr.getString(0) : "Misc";

			// As there are more than 400 business categories, dividing those into
			// 5 broad categories
			String[] food = { "Fast Food", "Burgers", "Food", "Breakfast & Brunch", "Cafes", "Hotels & Travel",
					"Chinese", "American (Traditional)", "Italian", "Pizza", "Gluten-Free", "Soup", "Seafood",
					"Bakeries", "Japanese", "Tapas/Small Plates", "Portuguese", "German", "Delis", "Fast Food",
					"Hot Dogs", "American (New)", "Barbeque", "Sushi Bars", "Diners", "Indian", "Sandwiches", "Mexican",
					"Kitchen & Bath", "Chicken Wings", "Greek", "Steakhouses", "Cafeteria", "Korean", "Vietnamese",
					"Thai", "Middle Eastern", "Asian Fusion", "Southern", "Soul Food", "Latin American", "French",
					"Wine Bars", "Caribbean", "Fondue", "Pakistani", "Vegetarian", "Ethiopian", "Taiwanese",
					"Cheesesteaks", "Grocery", "Local Flavor", "VitambusinessInput & Supplements", "Tex-Mex",
					"Hawaiian", "Mediterranean", "Cuban", "Food Banks", "Comfort Food", "Salad", "Fish & Chips",
					"Vegan", "Persian/Iranian", "Filipino" };

			List<String> ls1 = new ArrayList<String>();
			for (int s = 0; s < food.length; s++) {
				ls1.add(s, food[s]);
			}

			String[] medical = { "Health & Medical", "Optometrists", "Health Markets", "Skin Care",
					"Pediatric Dentists", "Endodontists", "Doctors", "Midwives", "Acupuncture", "Dermatologists",
					"Recreation Centers", "Fitness & businessInputtruction" };
			List<String> ls2 = new ArrayList<String>();
			for (int s = 0; s < medical.length; s++) {
				ls2.add(s, medical[s]);
			}

			String[] services = { "Auto Repair", "Home Services", "Veterinarians", "Libraries", "Automotive",
					"Local Services", "Pet Services", "Event Planning & Services", "Oil Change Stations",
					"Beauty & Spas", "Hair Salons", "Barbers", "Real Estate Services", "Keys & Locksmiths",
					"Property Management", "Transmission Repair", "Banks & Credit Unions", "Computers",
					"Lighting Fixtures & Equipment", "Print Media", "Car Wash", "Party Supplies", "Caterers",
					"Real Estate", "Plumbing", "Post Offices", "Building Supplies", "Pet  roomers", "Buffets",
					"Professional Services", "Public Services & Government", "Employment Agencies", "Nail Salons",
					"Electricians", "Videos & Video Game Rental", "Financial Services",
					"Heating & Air  onditioning/HVAC", "Country Dance Halls", "Weight Loss Centers", "Roofing",
					"Wedding Planning", "Home  leaning", "Movers", "Community Centers", "Gas & Service Stations",
					"Venues & Event Spaces", "Boat Charters", "Accountants", "Car Stereo businessInputtallation",
					"Religious Organizations", "Art Supplies", "Permanent Makeup", "Makeup Artists", "Gold Buyers",
					"Mortgage Brokers", "Photographers", "Laboratory  testing", "Diagnostic Services",
					"Pool & Hot Tub Service", "Fireplace Services", "Gardeners", "Chimney Sweeps",
					"Party Equipment Rentals", "Masonry/Concrete", "Windows businessInputtallation", "Flooring",
					"Door Sales/businessInputtallation", "Carpet businessInputtallation",
					"Departments of Motor Vehicles", "Damage Restoration", "Eyelash Service", "Real Estate Agents",
					"Painters", "Home Decor", "Solar businessInputtallation", "Commercial Real Estate",
					"Interior Design", "Web Design", "Home Organization", "Tree Services", "Office Equipment",
					"Home Window Tinting", "Wedding Chapels", "Motorcycle Repair", "Party & Event Planning",
					"Talent Agencies", "Video/Film Production", "Public Relations" };
			List<String> ls3 = new ArrayList<String>();
			for (int s = 0; s < services.length; s++) {
				ls3.add(s, services[s]);
			}

			String[] shoppent = { "Nightlife", "Active Life", "Shopping", "Bars", "Pubs", "Arts & Crafts", "Gift Shops",
					"Department Stores", "Arts & Entertainment", "Books", "Mattresses", "Lingerie", "Women's Clothing",
					"Fashion", "Men's Clothing", "Discount Store", "Tattoo", "Flowers & Gifts", "Tires", "Pets",
					"Hardware Stores", "Art Schools", "Museums", "Dive Bars", "Professional Sports Teams", "Polish",
					"Music & DVDs", "DJs", "Shopping Centers", "Tapas Bars", "Cards & Stationery", "Opera & Ballet",
					"Musical businessInputtruments & Teachers", "Tobacco Shops", "Furniture Stores", "Day Spas",
					"Ski & Snowboard Shops", "Children's Clothing", "Electronics", "Adult Entertainment",
					"Tanning Beds", "Country Clubs", "Tanning", "Mass Media", "Waxing", "Hobby Shops", "Cheese Shops",
					"Jazz & Blues", "Battery Stores", "Gastropubs", "Pool & Billiards", "Resorts", "Piercing",
					"Dive Shops", "Golf", "Pawn Shops", "Go Karts", "Swimming Pools", "Dance Schools", "Dance Studios",
					"Bingo Halls", "Casinos" };
			List<String> ls4 = new ArrayList<String>();
			for (int s = 0; s < shoppent.length; s++) {
				ls4.add(s, shoppent[s]);
			}

			String[] misc = { "Sporting Goods", "Thrift Stores", "Animal Shelters", "Churches", "Security Systems",
					"Bridal", "Used", "Newspapers & Magazines", "Massage", "Train Stations", "Peruvian",
					"Colleges & Universities", "Landmarks & Historical Buildings", "Landscaping", "Shades & Blinds",
					"Hot Tub & Pool", "Cosmetology Schools", "High Fidelity Audio Equipment", "Dim Sum" };
			List<String> ls5 = new ArrayList<String>();
			for (int s = 0; s < misc.length; s++) {
				ls5.add(s, misc[s]);
			}

			if (ls1.contains(tmp)) {
				tmp = "Food";
			} else if (ls2.contains(tmp)) {
				tmp = "Health&Fitness";
			} else if (ls3.contains(tmp)) {
				tmp = "Services";
			} else if (ls4.contains(tmp)) {
				tmp = "Shopping&Ent";
			} else {
				tmp = "Misc";
			}

			System.out.println("ID: " + id + " Name: " + name + " Category: " + tmp + " Stars: " + stars
					+ " Review Count: " + revCount + " Checkins: " + checkinCounter);
			if (checkinCounter > 0) {
				out.println(id + "," + name + "," + tmp + "," + stars + "," + revCount + "," + checkinCounter);
				rowCount++;
			}
		}
		out.flush();
		System.out.println("\n\n number of rows: " + rowCount);
	}
}