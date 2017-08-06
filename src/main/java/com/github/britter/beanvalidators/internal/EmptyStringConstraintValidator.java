/*
 * Copyright 2017 Benedikt Ritter
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
package com.github.britter.beanvalidators.internal;

import com.github.britter.beanvalidators.Empty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidatorContext;

public final class EmptyStringConstraintValidator implements NullAcceptingConstraintValidator<Empty, String> {

    @Override
    public boolean isValidNonNullValue(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value);
    }
}
