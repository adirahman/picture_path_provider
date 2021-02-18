package com.arc.picture_path_provider;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** PicturePathProviderPlugin */
public class PicturePathProviderPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "picture_path_provider");
    channel.setMethodCallHandler(this);
  }


  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "picture_path_provider");
    channel.setMethodCallHandler(new PicturePathProviderPlugin());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPicturesPath")) {
      String path;
      try{
        path = getPicturesPath();
        result.success(path);
      }catch (Exception e){
        Log.e("pictures_path_provider","Error: "+e.getMessage());
        result.error("Error","Error while getting path. Message: "+e.getMessage(),e);
      }
    } else {
      result.notImplemented();
    }
  }

  @TargetApi(Build.VERSION_CODES.FROYO)
  private String getPicturesPath(){
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
