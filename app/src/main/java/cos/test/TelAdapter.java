package cos.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TelAdapter extends ArrayAdapter<Telecast> {

    private List<Telecast> telList;

    public TelAdapter(Context context, List<Telecast> telList) {
        super(context, R.layout.tel_item, telList);
        this.telList = telList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tel_item, parent, false);
        }


        Telecast telecast = telList.get(position);

        TextView time = (TextView)view.findViewById(R.id.tvTime);
        TextView name = (TextView)view.findViewById(R.id.tvName);

        int currentTelcast = getCurrentTelecast();

        // currentTelcast - 1 делаем для того, что бы текущая программа не закрашивалась
        if (position < currentTelcast - 1) {
            view.setBackgroundResource(R.color.green_light);
        }else {
            view.setBackgroundResource(R.color.white);
        }
        time.setText(telecast.getTime());
        name.setText(telecast.getName());

        return view;
    }

    /**
     * Метод возвращает номер текущей передачи в telList
     * @return currentTelcast
     */
    private int getCurrentTelecast() {
        // Получение текущего времени. HH - часы, MM - минуты
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String currentTime = format.format(date);
        String[] sCurrTime = currentTime.split(":");
        int currTimeHH = Integer.parseInt(sCurrTime[0]);
        int currTimeMM = Integer.parseInt(sCurrTime[1]);

        int currentTelcast = 0;
        boolean flag = true;

        while (flag) {
            // Время в telList. HH - часы, MM - минуты
            String time = telList.get(currentTelcast).getTime();
            String[] sTime = time.split("\\.");
            int timeHH = Integer.parseInt(sTime[0]);
            int timeMM = Integer.parseInt(sTime[1]);

            if ((currTimeHH < timeHH) || ((currTimeHH == timeHH) && (currTimeMM < timeMM))) {
                flag = false;
                continue;
            }
            currentTelcast ++;
        }
        return currentTelcast;
    }
}
