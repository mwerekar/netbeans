/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_netbeans_lib_profiler_server_system_Threads */

#ifndef _Included_org_netbeans_lib_profiler_server_system_Threads
#define _Included_org_netbeans_lib_profiler_server_system_Threads
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    recordProfilerOwnThreads
 * Signature: (ZLjava/lang/Thread;)I
 */
JNIEXPORT jint JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_recordProfilerOwnThreads
  (JNIEnv *, jclass, jboolean, jobject);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    recordAdditionalProfilerOwnThread
 * Signature: (Ljava/lang/Thread;)V
 */
JNIEXPORT void JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_recordAdditionalProfilerOwnThread
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    getTotalNumberOfThreads
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_getTotalNumberOfThreads
  (JNIEnv *, jclass);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    suspendTargetAppThreads
 * Signature: (Ljava/lang/Thread;)V
 */
JNIEXPORT void JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_suspendTargetAppThreads
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    resumeTargetAppThreads
 * Signature: (Ljava/lang/Thread;)V
 */
JNIEXPORT void JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_resumeTargetAppThreads
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    terminateTargetAppThreads
 * Signature: (Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_terminateTargetAppThreads
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    targetAppThreadsExist
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_targetAppThreadsExist
  (JNIEnv *, jclass);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    getAllThreads
 * Signature: ([Ljava/lang/Thread;)[Ljava/lang/Thread;
 */
JNIEXPORT jobjectArray JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_getAllThreads
  (JNIEnv *, jclass, jobjectArray);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    getThreadsStatus
 * Signature: ([Ljava/lang/Thread;[I)V
 */
JNIEXPORT void JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_getThreadsStatus
  (JNIEnv *, jclass, jobjectArray, jintArray);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    getJVMArguments
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_getJVMArguments
  (JNIEnv *, jclass);

/*
 * Class:     org_netbeans_lib_profiler_server_system_Threads
 * Method:    getJavaCommand
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_netbeans_lib_profiler_server_system_Threads_getJavaCommand
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
