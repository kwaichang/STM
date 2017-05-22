package com.utas.stm.stm;


import android.content.Context;
import android.os.StrictMode;

public class Task {

        private String id, codeF, taskF, timeF, dateF, weightingF, urgencyF;

        public Task() {
        }

        public Task makeTask(Context ctx, String id, String code, String task, String time, String date, String weighting, String urgency) {

            Task p = new Task();

            p.setID(id);
            p.setTask(task);
            p.setCode(code);
            p.setTime(time);
            p.setDate(date);
            p.setWeighting(weighting);
            p.setUrgency(urgency);
            return (p);
        }

        public String getID() { return id; }
        public void setID(String s) { this.id = s; }

        public String getCode() { return codeF; }
        public void setCode(String s) { codeF = s; }

        public String getTask() { return taskF; }
        public void setTask(String s) { taskF = s; }

        public String getTime() { return timeF; }
        public void setTime(String s) { timeF = s; }

        public String getDate() { return dateF; }
        public void setDate(String s) { dateF = s; }

        public String getWeighting() { return weightingF; }
        public void setWeighting(String s) { weightingF = s; }

        public String getUrgency() { return urgencyF; }
        public void setUrgency(String s) { urgencyF = s; }
}
