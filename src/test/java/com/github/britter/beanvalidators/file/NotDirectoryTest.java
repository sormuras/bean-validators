/*
 * Copyright 2015 Benedikt Ritter
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
package com.github.britter.beanvalidators.file;

import javax.validation.ValidationException;
import java.io.File;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class NotDirectoryTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @Before
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must not be a directory");
    }

    @Test
    public void shouldValidateNull() throws Exception {
        fileBean.dir = null;

        validator.assertNoViolations("dir");
    }

    @Test
    public void shouldNotValidateDirectory() throws Exception {
        fileBean.dir = tmpFolder.newFolder();

        validator.assertViolation("dir");
    }

    @Test
    public void shouldValidateFile() throws Exception {
        fileBean.dir = tmpFolder.newFile();

        validator.assertNoViolations("dir");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        fileBean.path = " ";

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldValidateStringRepresentingFile() throws Exception {
        fileBean.path = tmpFolder.newFile().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingDirectory() throws Exception {
        fileBean.path = tmpFolder.newFolder().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenOtherTypeisAnnotated() throws Exception {
        validator.validate("object");
    }

    private static final class FileBean {
        @NotDirectory
        private File dir;
        @NotDirectory
        private String path;
        @NotDirectory
        private Object object = new Object();
    }
}
