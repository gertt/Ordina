package com.ordinefacile.root.ordinefacile.ui.scan;

import android.content.DialogInterface;

/**
 * Created by root on 1/20/18.
 */

public interface ScannerView {

  void checkIfPermissionNeeded();

  void requestPermission();

  boolean checkPermission();

  void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener);

  void goToMenuActivity();

  void getAppLanguageIt();

  void getAppLanguageEn();

}