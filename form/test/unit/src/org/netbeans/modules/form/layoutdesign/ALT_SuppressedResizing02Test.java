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

package org.netbeans.modules.form.layoutdesign;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.openide.filesystems.FileUtil;

public class ALT_SuppressedResizing02Test extends LayoutTestCase {

    public ALT_SuppressedResizing02Test(String name) {
        super(name);
        try {
	    className = this.getClass().getName();
	    className = className.substring(className.lastIndexOf('.') + 1, className.length());
            startingFormFile = FileUtil.toFileObject(new File(url.getFile() + goldenFilesPath + className + "-StartingForm.form").getCanonicalFile());
        } catch (IOException ioe) {
            fail(ioe.toString());
        }
    }

    /**
     * Resize (shrink) jButton2 to same size with jButton1.
     */
    public void doChanges0() {
        ld.externalSizeChangeHappened();
        // > UPDATE CURRENT STATE
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        contInterior.put("Form", new Rectangle(0, 0, 400, 300));
        compBounds.put("jButton2", new Rectangle(114, 73, 73, 23));
        baselinePosition.put("jButton2-73-23", new Integer(15));
        compBounds.put("jButton1", new Rectangle(114, 44, 49, 23));
        baselinePosition.put("jButton1-49-23", new Integer(15));
        compMinSize.put("Form", new Dimension(197, 107));
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        prefPaddingInParent.put("Form-jButton2-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton1-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        ld.updateCurrentState();
        // < UPDATE CURRENT STATE
        // > START RESIZING
        baselinePosition.put("jButton2-73-23", new Integer(15));
        compPrefSize.put("jButton2", new Dimension(73, 23));
        {
            String[] compIds = new String[]{"jButton2"};
            Rectangle[] bounds = new Rectangle[]{new Rectangle(114, 73, 73, 23)};
            Point hotspot = new Point(184, 84);
            int[] resizeEdges = new int[]{1, -1};
            boolean inLayout = true;
            ld.startResizing(compIds, bounds, hotspot, resizeEdges, inLayout);
        }
        // < START RESIZING
        prefPaddingInParent.put("Form-jButton2-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        // > MOVE
        // > MOVE
        {
            Point p = new Point(167, 83);
            String containerId = "Form";
            boolean autoPositioning = true;
            boolean lockDimension = false;
            Rectangle[] bounds = new Rectangle[]{new Rectangle(114, 73, 49, 23)};
            ld.move(p, containerId, autoPositioning, lockDimension, bounds);
        }
        // < MOVE
        prefPaddingInParent.put("Form-jButton2-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        // > MOVE
        // > MOVE
        {
            Point p = new Point(166, 83);
            String containerId = "Form";
            boolean autoPositioning = true;
            boolean lockDimension = false;
            Rectangle[] bounds = new Rectangle[]{new Rectangle(114, 73, 49, 23)};
            ld.move(p, containerId, autoPositioning, lockDimension, bounds);
        }
        // < MOVE
        // > END MOVING
        prefPaddingInParent.put("Form-jButton1-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton1-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        compMinSize.put("jButton1", new Dimension(73, 23));
        ld.endMoving(true);
        // < END MOVING
        ld.externalSizeChangeHappened();
        // > UPDATE CURRENT STATE
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        contInterior.put("Form", new Rectangle(0, 0, 400, 300));
        compBounds.put("jButton2", new Rectangle(114, 73, 49, 23));
        baselinePosition.put("jButton2-49-23", new Integer(15));
        compBounds.put("jButton1", new Rectangle(114, 44, 49, 23));
        baselinePosition.put("jButton1-49-23", new Integer(15));
        compMinSize.put("Form", new Dimension(173, 107));
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        compPrefSize.put("jButton2", new Dimension(73, 23));
        prefPaddingInParent.put("Form-jButton2-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton1-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        contInterior.put("Form", new Rectangle(0, 0, 400, 300));
        compBounds.put("jButton2", new Rectangle(114, 73, 49, 23));
        baselinePosition.put("jButton2-49-23", new Integer(15));
        compBounds.put("jButton1", new Rectangle(114, 44, 49, 23));
        baselinePosition.put("jButton1-49-23", new Integer(15));
        compMinSize.put("Form", new Dimension(173, 107));
        compBounds.put("Form", new Rectangle(0, 0, 400, 300));
        compPrefSize.put("jButton2", new Dimension(73, 23));
        prefPaddingInParent.put("Form-jButton2-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton1-0-1", new Integer(10)); // parentId-compId-dimension-compAlignment
        prefPaddingInParent.put("Form-jButton2-1-1", new Integer(11)); // parentId-compId-dimension-compAlignment
        ld.updateCurrentState();
        // < UPDATE CURRENT STATE
    }

}
