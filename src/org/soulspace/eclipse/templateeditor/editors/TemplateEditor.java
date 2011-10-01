package org.soulspace.eclipse.templateeditor.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class TemplateEditor extends TextEditor {

	private ColorManager colorManager;

	public TemplateEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new TmplConfiguration(colorManager));
		setDocumentProvider(new TmplDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
