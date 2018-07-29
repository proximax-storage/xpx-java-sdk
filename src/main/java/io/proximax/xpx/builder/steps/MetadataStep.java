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

package io.proximax.xpx.builder.steps;

import java.util.Map;


/**
 * The Interface MetadataStep.
 *
 * @param <T> the generic type
 */
public interface MetadataStep<T> {
    
    /**
     * Metadata.
     *
     * @param metadata the metadata
     * @return the t
     */
    T metadata(Map<String, String> metadata);
}
