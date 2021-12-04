package web.core.web.utils;

import web.core.service.impl.*;

public class SingletonServiceUtil {

    private static UserServiceImpl userServiceImpl = null;
    private static RoleServiceImpl roleServiceImpl = null;
    private static ListenGuidelineServiceImpl listenGuidelineServiceImpl = null;
    private static CommentServiceImpl commentServiceImpl = null;
    private static ExaminationQuestionServiceImpl examinationQuestionServiceImpl = null;
    private static ExaminationServiceImpl examinationServiceImpl = null;
    private static ExerciseServiceImpl exerciseServiceImpl = null;
    private static ExerciseQuestionServiceImpl exerciseQuestionServiceImpl = null;
    private static ResultServiceImpl resultServiceImpl = null;


    public static UserServiceImpl getUserServiceImplInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    public static RoleServiceImpl getRoleServiceImplInstance() {
        if (roleServiceImpl == null) {
            roleServiceImpl = new RoleServiceImpl();
        }
        return roleServiceImpl;
    }

    public static ListenGuidelineServiceImpl getListenGuidelineServiceImplInstance() {
        if (listenGuidelineServiceImpl == null) {
            listenGuidelineServiceImpl = new ListenGuidelineServiceImpl();
        }
        return listenGuidelineServiceImpl;
    }

    public static CommentServiceImpl getCommentServiceImplInstance() {
        if (commentServiceImpl == null) {
            commentServiceImpl = new CommentServiceImpl();
        }
        return commentServiceImpl;
    }

    public static ExaminationServiceImpl getExaminationServiceImplInstance() {
        if (examinationServiceImpl == null) {
            examinationServiceImpl = new ExaminationServiceImpl();
        }

        return examinationServiceImpl;
    }

    public static ExaminationQuestionServiceImpl getExaminationQuestionServiceImplInstance() {
        if (examinationQuestionServiceImpl == null) {
            examinationQuestionServiceImpl = new ExaminationQuestionServiceImpl();
        }
        return examinationQuestionServiceImpl;
    }

    public static ExerciseServiceImpl getExerciseServiceImplInstance() {
        if (exerciseServiceImpl == null) {
            exerciseServiceImpl = new ExerciseServiceImpl();
        }
        return  exerciseServiceImpl;
    }

    public static ExerciseQuestionServiceImpl getExerciseQuestionServiceImplInstance() {
        if (exerciseQuestionServiceImpl == null) {
            exerciseQuestionServiceImpl = new ExerciseQuestionServiceImpl();
        }

        return exerciseQuestionServiceImpl;
    }

    public static ResultServiceImpl getResultServiceImplInstance() {
        if (resultServiceImpl == null) {
            resultServiceImpl = new ResultServiceImpl();
        }
        return resultServiceImpl;
    }

}
