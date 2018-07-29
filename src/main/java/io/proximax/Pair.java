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

package io.proximax;




/**
 * The Class Pair.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T23:45:59.064-04:00")
public class Pair {
    
    /** The name. */
    private String name = "";
    
    /** The value. */
    private String value = "";

    /**
     * Instantiates a new pair.
     *
     * @param name the name
     * @param value the value
     */
    public Pair (String name, String value) {
        setName(name);
        setValue(value);
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    private void setName(String name) {
        if (!isValidString(name)) return;

        this.name = name;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    private void setValue(String value) {
        if (!isValidString(value)) return;

        this.value = value;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Checks if is valid string.
     *
     * @param arg the arg
     * @return true, if is valid string
     */
    private boolean isValidString(String arg) {
        if (arg == null) return false;
        if (arg.trim().isEmpty()) return false;

        return true;
    }
}
