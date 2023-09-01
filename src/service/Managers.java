package service;

import history.HistoryManager;
import history.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.TaskManager;

public final class Managers {
   private  Managers(){};

   public static TaskManager getDefault(){
      return new InMemoryTaskManager();
   }

   public static HistoryManager getDefaultHistory(){
      return new InMemoryHistoryManager();
   }
}
