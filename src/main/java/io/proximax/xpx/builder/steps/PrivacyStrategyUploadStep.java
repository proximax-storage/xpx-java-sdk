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

import io.proximax.xpx.strategy.privacy.PrivacyStrategy;

import java.util.List;
import java.util.Map;

import static io.proximax.xpx.strategy.privacy.SecuredWithShamirSecretSharingPrivacyStrategy.*;


/**
 * The Interface PrivacyStrategyUploadStep.
 *
 * @param <T> the generic type
 */
public interface PrivacyStrategyUploadStep<T> {

    /**
     * Privacy strategy.
     *
     * @param privacyStrategy the privacy strategy
     * @return the t
     */
    T privacyStrategy(PrivacyStrategy privacyStrategy);

    /**
     * Plain privacy.
     *
     * @return the t
     */
    T plainPrivacy();

    /**
     * Secured with proximax keys privacy strategy.
     *
     * @return the t
     */
    T securedWithNemKeysPrivacyStrategy();

    /**
     * Secured with password privacy strategy.
     *
     * @param password the password
     * @return the t
     */
    T securedWithPasswordPrivacyStrategy(String password);

    T securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                    SecretPart... secretParts);

    T securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                    List<SecretPart> secretParts);

    T securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                    Map<Integer, byte[]> secretParts);


}
