package ke.co.milleradulu.milleradulu.fadhili;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Donation;
import ke.co.milleradulu.milleradulu.fadhili.apihandler.models.Purchase;

public class SessionManagement {
  SharedPreferences pref;
  SharedPreferences.Editor editor;
  Context _context;
  Gson gson = new Gson();

  int PRIVATE_MODE = 0;

  private static final String PREF_NAME = "FadhiliPrefs";

  private static final String IS_LOGIN = "IsLoggedIn";

  public static final String KEY_NAME = "donorName";

  public static final String KEY_ID = "donorId";

  public static final String KEY_POST_ID = "postId";

  public static final String KEY_DONATION_ITEM_ID = "donationItemId";

  public static final String KEY_CART_ITEMS = "cartItems";

  public static final String KEY_CART_TOTAL = "cartItemsTotal";

  public SessionManagement(Context context) {
    this._context = context;
    pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    editor = pref.edit();
  }

  public void createLoginSession(String donorId,String donorName) {

    editor.putBoolean(IS_LOGIN, true);

    editor.putString(KEY_ID, donorId);
    editor.putString(KEY_NAME, donorName);

    editor.commit();
  }

  public void checkLogin() {
    if (!this.isLoggedIn()) {

      Intent i = new Intent(_context, LoginActivity.class);
      i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      _context.startActivity(i);
    }
  }

  public boolean isLoggedIn(){
    return pref.getBoolean(IS_LOGIN, false);
  }

  public HashMap<String, String> getUserDetails() {
    HashMap<String, String> donor = new HashMap<>();

    donor.put(KEY_ID, pref.getString(KEY_ID, null));
    donor.put(KEY_NAME, pref.getString(KEY_NAME, null));

    return donor;
  }

  public void setKeyPostId(String postId) {
    editor.putString(KEY_POST_ID, postId);
    editor.commit();
  }
  public void setKeyDonationItemId(String donationItemId) {
    editor.putString(KEY_DONATION_ITEM_ID, donationItemId);
    editor.commit();
  }

  public String getPostId() {
    return pref.getString(KEY_POST_ID, null);
  }

  public String getKeyDonationItemId() {
    return pref.getString(KEY_DONATION_ITEM_ID, null);
  }

  public String getDonorId() {
    return pref.getString(KEY_ID, null);
  }

  public void setCartItems(ArrayList<Purchase> cartItems) {

    ArrayList<Purchase> result = new ArrayList<>();
    HashSet<Purchase> set = new HashSet<>();

    for(Purchase purchase : cartItems) {
      if(!set.contains(purchase)) {
        result.add(purchase);
        set.add(purchase);
      }
    }

    String json = gson.toJson(result);

    editor.putString(KEY_CART_ITEMS, json);
    editor.commit();
  }

  public ArrayList<Purchase> getCartItems() {
    String json = pref.getString(KEY_CART_ITEMS, null);

    Type type = new TypeToken<ArrayList<Purchase>>() {}.getType();

    return gson.fromJson(json, type);
  }

  public void setKeyCartTotal(String total) {
    editor.putString(KEY_CART_TOTAL, total);
    editor.commit();
  }

  public String getKeyCartTotal() {
    return pref.getString(KEY_CART_TOTAL, null);
  }

  public void logoutUser() {
    editor.clear();
    editor.commit();

    Intent i = new Intent(_context, LoginActivity.class);

    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    _context.startActivity(i);
  }
}
