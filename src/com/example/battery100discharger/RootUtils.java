package com.example.battery100discharger;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.widget.Toast;

public class RootUtils {
	/**
	 * Execute terminal commands on rooted device
	 * @param cmds
	 * @throws IOException
	 */
    public static void runAsRoot(String[] cmds) throws IOException{
        Process p = Runtime.getRuntime().exec("su");
        DataOutputStream os = new DataOutputStream(p.getOutputStream());            
        for (String tmpCmd : cmds) {
                os.writeBytes(tmpCmd+"\n");
        }           
        os.writeBytes("exit\n");  
        os.flush();
    }
    
    /**
     * Checks if the phone is rooted.
     * http://stackoverflow.com/questions/1101380/determine-if-running-on-a-rooted-device
     * @return <code>true</code> if the phone is rooted, <code>false</code>
     * otherwise.
     */
    public static boolean isDeviceRooted() {

      // get from build info
      String buildTags = android.os.Build.TAGS;
      if (buildTags != null && buildTags.contains("test-keys")) {
        return true;
      }
      // check if /system/app/Superuser.apk is present
      try {
        File file = new File("/system/app/Superuser.apk");
        if (file.exists()) {
          return true;
        }
      } catch (Throwable e1) {
        // ignore
      }
      return false;
    }
    
    /**
     * Перезагрузка устройства
     */
    public static boolean runRebootcmd(Context context){
    	String[] cmds = { Commands.REBOOT };
    	try {
			RootUtils.runAsRoot(cmds);
		} catch (IOException e) {
			Toast.makeText(context, R.string.toast_error, Toast.LENGTH_LONG).show();
			return false;
		}
    	Toast.makeText(context, R.string.toast_reboot, Toast.LENGTH_LONG).show();
    	return true;
    }
    
    /**
     *  Сброс контроллера питания (echo B8)
     */
    public static boolean runB8cmd(Context context){
    	String[] cmds = {
    			Commands.ECHO_B8, 
    			Commands.ECHO_B820 
    			};
    	try {
    		RootUtils.runAsRoot(cmds);
		} catch (IOException e) {
			Toast.makeText(context, R.string.toast_error, Toast.LENGTH_LONG).show();
			return false;
		}
    	Toast.makeText(context, R.string.toast_b8, Toast.LENGTH_LONG).show();
    	return true;
    }
    
    /**
     * Сброс регистров
     */
    public static boolean run08cmd(Context context){
    	String[] cmds = {
    			Commands.ECHO_08, 
    			Commands.ECHO_0800 
    			};
    	try {
    		RootUtils.runAsRoot(cmds);
		} catch (IOException e) {
			Toast.makeText(context, R.string.toast_error, Toast.LENGTH_LONG).show();
			return false;
		}
    	Toast.makeText(context, R.string.toast_08, Toast.LENGTH_LONG).show();
    	return true;
    }
}
