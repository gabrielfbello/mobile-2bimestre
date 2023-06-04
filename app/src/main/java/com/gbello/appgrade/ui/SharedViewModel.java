package com.gbello.appgrade.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isGradeUpdated = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsGradeUpdated() {
        return isGradeUpdated;
    }

    public void setIsGradeUpdated(Boolean isUpdated) {
        isGradeUpdated.setValue(isUpdated);
    }
}
