// Modifications copyright (C) 2018, Baidu.com, Inc.
// Copyright 2018 The Apache Software Foundation

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.common.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class DeepCopy {
    private static final Logger LOG = LogManager.getLogger(DeepCopy.class);

    public static boolean copy(Writable orig, Writable copied) {
        FastByteArrayOutputStream byteArrayOutputStream = new FastByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);
        try {
            orig.write(out);
            out.flush();
            out.close();

            DataInputStream in = new DataInputStream(byteArrayOutputStream.getInputStream());
            copied.readFields(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warn("failed to copy object.", e);
            return false;
        }
        return true;
    }
}
