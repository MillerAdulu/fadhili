package ke.co.milleradulu.milleradulu.fadhili;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {
  SharedPreferences pref;

  SharedPreferences.Editor editor;

  Context _context;

  int PRIVATE_MODE = 0;

  private static final String PREF_NAME = "FadhiliPrefs";

  private static final String IS_LOGIN = "IsLoggedIn";

  public static final String KEY_NAME = "donorName";

  public static final String KEY_ID = "donorId";

  SessionManagement(Context context){
    this._context = context;
    pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    editor = pref.edit();
  }

  public void createLoginSession(String donorId,String donorName){

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

  public HashMap<String, String> getUserDetails(){
    HashMap<String, String> donor = new HashMap<>();

    donor.put(KEY_ID, pref.getString(KEY_ID, null));
    donor.put(KEY_NAME, pref.getString(KEY_NAME, null));

    return donor;
  }

  public void logoutUser(){
    editor.clear();
    editor.commit();

    Intent i = new Intent(_context, LoginActivity.class);

    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    _context.startActivity(i);
  }
}
