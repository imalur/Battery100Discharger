package com.example.battery100discharger;

public class Commands {
	public static final String ECHO_B8 = "echo B8 > /sys/devices/i2c-2/2-0034/axp20_reg";
	public static final String ECHO_B820 = "echo B820 > /sys/devices/i2c-2/2-0034/axp20_reg"; 
	public static final String ECHO_08 = "echo 08 > /sys/devices/i2c-2/2-0034/axp20_reg"; 
	public static final String ECHO_0800 = "echo 0800 > /sys/devices/i2c-2/2-0034/axp20_reg"; 
	public static final String REBOOT = "reboot"; 
}
