package com.raghu.template;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by raghavendraguru on 6/20/17.
 */
public class TemplateDialog extends DialogWrapper {


    private LabeledComponent LabComponent;
    DialogContentForm dialogContentForm;

    protected TemplateDialog(Project project, boolean canBeParent) {
        super(project, canBeParent);
        setTitle("Create new Template Class");
        dialogContentForm = new DialogContentForm();
        LabComponent = LabeledComponent.create(dialogContentForm.getMainPanel(),"sample");
        init();

    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return LabComponent;
    }

}
