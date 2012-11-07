/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.android.dialer.dialpad;

import static com.android.dialer.dialpad.SmartDialAdapter.LOG_TAG;

import android.util.Log;

import java.util.ArrayList;

/**
 * Stores information about a range of characters matched in a display name The integers
 * start and end indicate that the range start to end (exclusive) correspond to some characters
 * in the query. Used by {@link SmartDialAdapter} to highlight certain parts of the contact's
 * display name to indicate that those ranges matched the user's query.
 */
class SmartDialMatchPosition {
    public int start;
    public int end;

    public SmartDialMatchPosition(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private void advance(int toAdvance) {
        this.start += toAdvance;
        this.end += toAdvance;
    }

    /**
     * Used by {@link SmartDialNameMatcher} to advance the positions of a match position found in
     * a sub query.
     *
     * @param inList ArrayList of SmartDialMatchPositions to modify.
     * @param toAdvance Offset to modify by.
     */
    public static void advanceMatchPositions(ArrayList<SmartDialMatchPosition> inList,
            int toAdvance) {
        for (int i = 0; i < inList.size(); i++) {
            inList.get(i).advance(toAdvance);
        }
    }

    /**
     * Used mainly for debug purposes. Displays contents of an ArrayList of SmartDialMatchPositions.
     *
     * @param list ArrayList of SmartDialMatchPositions to print out in a human readable fashion.
     */
    public static void print(ArrayList<SmartDialMatchPosition> list) {
        for (int i = 0; i < list.size(); i ++) {
            SmartDialMatchPosition m = list.get(i);
            Log.d(LOG_TAG, "[" + m.start + "," + m.end + "]");
        }
    }
}
