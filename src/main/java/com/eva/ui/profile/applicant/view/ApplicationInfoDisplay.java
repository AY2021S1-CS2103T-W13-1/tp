package com.eva.ui.profile.applicant.view;

import com.eva.model.person.applicant.application.Application;
import com.eva.ui.UiPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A UI component that displays the application info.
 */
public class ApplicationInfoDisplay extends UiPart<Region> {
    private static final String FXML = "ApplicationInfoDisplay.fxml";

    public final Application currentApplication;

    @FXML
    private HBox displayPane;
    @FXML
    private Label application;

    /**
     * Creates an application info display with the current application.
     */
    ApplicationInfoDisplay(Application currentApplication) {
        super(FXML);
        this.currentApplication = currentApplication;
        application.setText(currentApplication.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationInfoDisplay)) {
            return false;
        }

        // state check
        ApplicationInfoDisplay card = (ApplicationInfoDisplay) other;
        return currentApplication.equals(card.currentApplication);
    }
}
