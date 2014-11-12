/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.eclipse.templateeditor.editors;

import org.eclipse.swt.graphics.RGB;

public interface ITmplColorConstants {
	RGB TMPL_COMMENT = new RGB(128, 128, 128);
	RGB PROC_INSTR = new RGB(128, 128, 128);
	RGB STRING = new RGB(0, 192, 0);
	RGB NUMBER = new RGB(192, 0, 0);
	RGB DEFAULT = new RGB(0, 0, 0);
	RGB CODE = new RGB(0, 0, 255);
	RGB CODE_COMMENT = new RGB(128, 128, 128);
}
