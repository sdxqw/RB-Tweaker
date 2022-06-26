package io.github.axst.module.render;

import io.github.axst.RebelCore;
import io.github.axst.module.ModuleBase;
import io.github.axst.utils.ui.RenderUtils;
import lombok.Getter;

import java.awt.*;

public abstract class ModuleRenderer extends ModuleBase {

    public int x;
    public int y;

    @Getter
    public int widthIn;
    @Getter
    public int heightIn;

    private static int lastDraggedMod = 0;

    public DraggableComponent draggableComponent;

    public ModuleRenderer(String name, String description, String icon, int x, int y) {
        super(name, description, icon);
        if(getSetting().size() >= 1) {
            draggableComponent = new DraggableComponent( x, y, getWidthIn(), getHeightIn() );
        } else if (getSetting().iterator().hasNext()) {
            draggableComponent = new DraggableComponent( x, y, getWidthIn(), getHeightIn() );
        } else {
            draggableComponent = new DraggableComponent( x, y, getWidthIn(), getHeightIn() );
        }
    }

    public abstract void drawModule();

    public void renderModule(int mouseX, int mouseY) {
        boolean hovered = mouseX >= this.getX() && mouseX <= this.getX() + this.getWidthIn() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeightIn();
        if (hovered) RenderUtils.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color( 0, 204, 255, 152)).getRGB());

        RenderUtils.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidthIn() + 3, this.getHeightIn() + 2, (new Color(170, 170, 170, 100)).getRGB());
    }

    public int getX() {
        return draggableComponent.getX();
    }

    public int getY() {
        return draggableComponent.getY();
    }

    public static void renderComponent(int mouseX, int mouseY) {

        boolean doDrag = true;

        for(ModuleBase module : RebelCore.getInstance().getModuleManager().getModules()) {
            if(module.isEnabled() && module instanceof ModuleRenderer) {
                ((ModuleRenderer)module).renderModule(mouseX, mouseY);
                if (module.hashCode() == lastDraggedMod && ((ModuleRenderer)module).draggableComponent.isDraggingModule(mouseX, mouseY)) doDrag = false;
            }
        }

        for(ModuleBase module : RebelCore.getInstance().getModuleManager().getModules()) {
            if (doDrag && module.isEnabled() && module instanceof ModuleRenderer && ((ModuleRenderer)module).draggableComponent.isDraggingModule(mouseX, mouseY)) {
                doDrag = false;
                lastDraggedMod = module.hashCode();
            }
        }
    }
}
