package src.GUI.Controller;

import src.GUI.Model.MRSModel;

public abstract class BaseController {

    private MRSModel model;

    public void setModel(MRSModel model) {
        this.model = model;
    }

    public MRSModel getModel() throws Exception {
        return new MRSModel();
    }

    public abstract void setup() throws Exception;
}
