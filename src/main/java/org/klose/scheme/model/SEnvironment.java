package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.ArrayList;
import java.util.List;

public class SEnvironment {
    private List<SFrame> frames = new ArrayList<>();

    public List<SFrame> getFrames() {
        return frames;
    }

    public void setFrames(List<SFrame> frames) {
        this.frames = frames;
    }

    public void addFrame(SFrame frame) {
        this.frames.add(frame);
    }

    public SObject lookup(String var) {
        for (SFrame f : frames) {
            if (f.contains(var))
                return f.find(var);
        }
        return null;
    }

    public void define(String var, SObject val) {
        SFrame firstFrame = frames.get(0);
        if (firstFrame == null) {
            firstFrame = new SFrame();
            frames.add(firstFrame);
        }
        firstFrame.bind(var, val);
    }

    public void assign(String var, SObject val) {
        for (SFrame frame : frames) {
            if (frame.contains(var)) {
                frame.bind(var, val);
                return;
            }
        }
        throw new RuntimeException("not found assigned variable");
    }
}
