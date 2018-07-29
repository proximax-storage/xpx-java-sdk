/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.utils;

import java.util.Scanner;






/**
 * The Class ScannerUtils.
 */
public class ScannerUtils {

	/**
	 * exit when enter string "exit".
	 */
	public static void monitorExit() {
		Scanner scanner = new Scanner(System.in);
		try{
	        while (true) { 
		        String line = scanner.nextLine();
		        if("exit".equals(line)){
		        	break;
		        }
	        }
		} catch (Exception ex) {
			
		} finally {
			scanner.close();
		}
	}
}
