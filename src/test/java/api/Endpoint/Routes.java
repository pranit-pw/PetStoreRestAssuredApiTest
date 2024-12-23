package api.Endpoint;

public class Routes {
	
	// Base URL
	public static String base_url = "https://petstore.swagger.io/v2";
	
	
	// User module End points
	// Create user
	public static String Post_url = base_url+"/user";
	// Get user
	public static String get_url = base_url+"/user/{username}";
	// Update user
	public static String update_url = base_url+"/user/{username}";
	// Delete user
	public static String delete_url = base_url+"/user/{username}";
	// Login user
	public static String get_url_login = base_url+"/user/login";
	// Logout user
	public static String get_url_logout = base_url+"/user/logout";
	
	
	
	// Store module End Points
	// Get store inventory
	public static String get_storeinventory_url = base_url+"/store/inventory";
	// Order from Store
	public static String post_placeorder_url = base_url+"/store/order";
	// Get store order details
	public static String get_storeorderdetails_url = base_url+"/store/order/{orderId}";
	// delete order from store
	public static String delete_storeorder_url = base_url+"/store/order/{orderId}";
	
	
	
	// Pet module End Points
	// Add new pet to store
	public static String post_addnewpet_url = base_url+"/pet";
	// Upload pet image
	public static String post_uploadpetimage_url = base_url+"/pet/{petId}/uploadImage";
	// update existing pet details
	public static String put_updatepetdetails_url = base_url+"/pet";
	// Find pet by id
	public static String get_findpetbyid_url = base_url+"/pet/{petId}";
	// find pet by status
	public static String get_findpetbystatus_url = base_url+"/pet/findByStatus";
	// Update pet with form data
	public static String post_updatepetwithformdata_url = base_url+"/pet/{petId}";
	// Delete pet from store
	public static String delete_deletepet_url = base_url+"/pet/{petId}";
	

}
