package controller;

import services.SessionManager;
import view.Constants;

public abstract class AuthenticatedUseCaseController extends UseCaseController {

	@Override
	public boolean preConditionVerified() {
		return SessionManager.getInstance().isUserAuthenticated();
	}

	@Override
	public void preConditionError() {
		mainView.showError(Constants.USE_CASE_PRECONDITION_ERROR, Constants.USE_CASE_PRECONDITION_RECOVERY);
	}
}
