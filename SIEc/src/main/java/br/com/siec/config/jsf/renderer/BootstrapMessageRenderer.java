package br.com.siec.config.jsf.renderer;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;
import com.sun.faces.renderkit.html_basic.OutputMessageRenderer;

public class BootstrapMessageRenderer extends HtmlBasicRenderer {

	// copy & paste from MessageRenderer

	private OutputMessageRenderer omRenderer = null;

	public BootstrapMessageRenderer() {
		omRenderer = new OutputMessageRenderer();
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {

		rendererParamsNotNull(context, component);

		if (component instanceof UIOutput) {
			omRenderer.encodeBegin(context, component);
		}

	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {

		rendererParamsNotNull(context, component);

		if (component instanceof UIOutput) {
			omRenderer.encodeChildren(context, component);
		}

	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {

		rendererParamsNotNull(context, component);

		if (component instanceof UIOutput) {
			omRenderer.encodeEnd(context, component);
			return;
		}

		if (!shouldEncode(component)) {
			return;
		}

		// If id is user specified, we must render
		boolean mustRender = shouldWriteIdAttribute(component);

		ResponseWriter writer = context.getResponseWriter();
		assert (writer != null);

		UIMessage message = (UIMessage) component;

		String clientId = message.getFor();
		// "for" attribute required for Message. Should be taken care of
		// by TLD in JSP case, but need to cover non-JSP case.
		if (clientId == null) {
			if (logger.isLoggable(Level.WARNING)) {
				logger.warning("'for' attribute cannot be null");
			}
			return;
		}

		clientId = augmentIdReference(clientId, component);
		Iterator<?> messageIter = getMessageIter(context, clientId, component);

		assert (messageIter != null);
		if (!messageIter.hasNext()) {
			if (mustRender) {
				// no message to render, but must render anyway
				writer.startElement("span", component);
				writeIdAttributeIfNecessary(context, writer, component);
				writer.endElement("span");
			} // otherwise, return without rendering
			return;
		}
		FacesMessage curMessage = (FacesMessage) messageIter.next();
		if (curMessage.isRendered() && !message.isRedisplay()) {
			return;
		}
		curMessage.rendered();

		String severityStyle = null;
		String severityStyleClass = null;
		boolean showSummary = message.isShowSummary();
		boolean showDetail = message.isShowDetail();

		// make sure we have a non-null value for summary and
		// detail.
		String summary = (null != (summary = curMessage.getSummary())) ? summary
				: "";
		// Default to summary if we have no detail
		String detail = (null != (detail = curMessage.getDetail())) ? detail
				: summary;

		if (curMessage.getSeverity() == FacesMessage.SEVERITY_INFO) {
			severityStyle = (String) component.getAttributes().get("infoStyle");
			severityStyleClass = (String) component.getAttributes().get(
					"infoClass");
		} else if (curMessage.getSeverity() == FacesMessage.SEVERITY_WARN) {
			severityStyle = (String) component.getAttributes().get("warnStyle");
			severityStyleClass = (String) component.getAttributes().get(
					"warnClass");
		} else if (curMessage.getSeverity() == FacesMessage.SEVERITY_ERROR) {
			severityStyle = (String) component.getAttributes()
					.get("errorStyle");
			severityStyleClass = (String) component.getAttributes().get(
					"errorClass");
		} else if (curMessage.getSeverity() == FacesMessage.SEVERITY_FATAL) {
			severityStyle = (String) component.getAttributes()
					.get("fatalStyle");
			severityStyleClass = (String) component.getAttributes().get(
					"fatalClass");
		}

		String style = (String) component.getAttributes().get("style");
		String styleClass = (String) component.getAttributes()
				.get("styleClass");
		String dir = (String) component.getAttributes().get("dir");
		String lang = (String) component.getAttributes().get("lang");
		String title = (String) component.getAttributes().get("title");

		// if we have style and severityStyle
		if ((style != null) && (severityStyle != null)) {
			// severityStyle wins
			style = severityStyle;
		}
		// if we have no style, but do have severityStyle
		else if ((style == null) && (severityStyle != null)) {
			// severityStyle wins
			style = severityStyle;
		}

		// if we have styleClass and severityStyleClass
		if ((styleClass != null) && (severityStyleClass != null)) {
			// severityStyleClass wins
			styleClass = severityStyleClass;
		}
		// if we have no styleClass, but do have severityStyleClass
		else if ((styleClass == null) && (severityStyleClass != null)) {
			// severityStyleClass wins
			styleClass = severityStyleClass;
		}

		// Bootstrap: customize style class
		styleClass = rendererSupport.getMessageStyleClass(curMessage);

		// Done intializing local variables. Move on to rendering.

		boolean wroteSpan = false;
		if (styleClass != null || style != null || dir != null || lang != null
				|| title != null || mustRender) {
			writer.startElement("span", component);
			writeIdAttributeIfNecessary(context, writer, component);

			wroteSpan = true;
			if (style != null) {
				writer.writeAttribute("style", style, "style");
			}
			if (styleClass != null) {
				writer.writeAttribute("class", styleClass, "styleClass");
			}
			if (dir != null) {
				writer.writeAttribute("dir", dir, "dir");
			}
			if (lang != null) {
				writer.writeAttribute(
						RenderKitUtils.prefixAttribute("lang", writer), lang,
						"lang");
			}
			if (title != null) {
				writer.writeAttribute("title", title, "title");
			}

			// Bootstrap: add icon
			rendererSupport.addMessageIcon(writer, curMessage);
		}

		Object val = component.getAttributes().get("tooltip");
		boolean isTooltip = (val != null) && Boolean.valueOf(val.toString());

		boolean wroteTooltip = false;
		if (showSummary && showDetail && isTooltip) {

			if (!wroteSpan) {
				writer.startElement("span", component);
			}
			if (title == null || title.length() == 0) {
				writer.writeAttribute("title", summary, "title");
			}
			writer.flush();
			writer.writeText("\t", component, null);
			wroteTooltip = true;
		} else if (wroteSpan) {
			writer.flush();
		}

		if (!wroteTooltip && showSummary) {
			writer.writeText("\t", component, null);
			writer.writeText(summary, component, null);
			writer.writeText(" ", component, null);
		}
		if (showDetail) {
			writer.writeText(detail, component, null);
		}

		if (wroteSpan || wroteTooltip) {
			writer.endElement("span");
		}
	}

	// Bootstrap support instance
	private final BootstrapRendererSupport rendererSupport = BootstrapRendererSupport
			.getInstance();

}
