package org.soulspace.eclipse.templateeditor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class TmplConfiguration extends SourceViewerConfiguration {
	private TmplDoubleClickStrategy doubleClickStrategy;
	private TmplCodeScanner tagScanner;
	private TmplScanner scanner;
	private ColorManager colorManager;

	public TmplConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			TmplPartitionScanner.TMPL_COMMENT,
			TmplPartitionScanner.TMPL_CODE };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new TmplDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected TmplScanner getTmplScanner() {
		if (scanner == null) {
			scanner = new TmplScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ITmplColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected TmplCodeScanner getTmplCodeScanner() {
		if (tagScanner == null) {
			tagScanner = new TmplCodeScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(ITmplColorConstants.CODE))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getTmplCodeScanner());
		reconciler.setDamager(dr, TmplPartitionScanner.TMPL_CODE);
		reconciler.setRepairer(dr, TmplPartitionScanner.TMPL_CODE);

		dr = new DefaultDamagerRepairer(getTmplScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(ITmplColorConstants.TMPL_COMMENT)));
		reconciler.setDamager(ndr, TmplPartitionScanner.TMPL_COMMENT);
		reconciler.setRepairer(ndr, TmplPartitionScanner.TMPL_COMMENT);

		return reconciler;
	}

}