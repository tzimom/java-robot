package de.tzimom.javarobot.graphics.rendering;

import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;
import de.tzimom.javarobot.graphics.rendering.dynamic.DynamicGraphics;
import de.tzimom.javarobot.graphics.rendering.dynamic.Vector2f;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Collection;

public class Display {
    private final JFrame frame;
    private final BufferStrategy bufferStrategy;
    private final Color backgroundColor;

    @SuppressWarnings("MagicConstant")
    public Display(Color backgroundColor, DisplayConfig displayConfig) {
        this.backgroundColor = backgroundColor;

        frame = new JFrame(displayConfig.title());

        frame.setSize(displayConfig.size());
        frame.setDefaultCloseOperation(displayConfig.defaultCloseOperation().getValue());
        frame.setVisible(true);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();
    }

    public void update(Collection<GraphicsComponent> components) {
        var graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        var width = frame.getWidth();
        var height = frame.getHeight();
        var scale = Math.min(width, height);

        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, width, height);

        var mainContentLocation = new Vector2f((width - scale) / 2f, (height - scale) / 2f);
        var dynamicGraphics = new DynamicGraphics(graphics, Math.min(width, height));

        graphics.translate(mainContentLocation.x(), mainContentLocation.y());
        components.forEach(component -> component.render(width, height, dynamicGraphics));
        graphics.translate(-mainContentLocation.x(), -mainContentLocation.y());

        bufferStrategy.show();
        graphics.dispose();
    }
}