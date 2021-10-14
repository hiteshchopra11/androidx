/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package androidx.appsearch.app;

/**
 * A class that encapsulates all features that are only supported with certain combinations of
 * backend and Android API Level.
 */
public interface Capabilities {

    /**
     * Returns whether or not {@link SearchResult.MatchInfo#getSubmatchRange} and
     * {@link SearchResult.MatchInfo#getSubmatch} are available.
     */
    boolean isSubmatchSupported();
}