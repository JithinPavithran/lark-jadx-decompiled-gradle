package com.bytedance.applog.p198b;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.b.a */
public abstract class AbstractC3384a {

    /* renamed from: a */
    protected HashSet<String> f10730a;

    /* renamed from: b */
    protected HashMap<String, HashSet<String>> f10731b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo13634a(String str);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo13636a(HashSet<String> hashSet, String str);

    /* renamed from: a */
    public static final AbstractC3384a m14147a(Context context) {
        Map<String, ?> map;
        AbstractC3384a aVar;
        Set set;
        Set set2;
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("sp_filter_name", 0);
            HashSet hashSet = new HashSet();
            HashMap hashMap = new HashMap();
            try {
                map = sharedPreferences.getAll();
            } catch (Throwable unused) {
                map = null;
            }
            if (map == null || map.size() <= 0) {
                return null;
            }
            int i = 0;
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (entry != null) {
                    String key = entry.getKey();
                    if ("is_block".equals(key)) {
                        i = sharedPreferences.getInt("is_block", 0);
                    } else if ("events".equals(key)) {
                        try {
                            set2 = (Set) entry.getValue();
                        } catch (Throwable unused2) {
                            set2 = null;
                        }
                        if (set2 != null && set2.size() > 0) {
                            hashSet.addAll(set2);
                        }
                    } else if (!TextUtils.isEmpty(key)) {
                        HashSet hashSet2 = new HashSet();
                        try {
                            set = (Set) entry.getValue();
                        } catch (Throwable unused3) {
                            set = null;
                        }
                        if (set != null && set.size() > 0) {
                            hashSet2.addAll(set);
                        }
                        if (hashSet2.size() > 0) {
                            hashMap.put(key, hashSet2);
                        }
                    }
                }
            }
            if (i > 0) {
                aVar = new C3386c(hashSet, hashMap);
            } else {
                aVar = new C3385b(hashSet, hashMap);
            }
            return aVar;
        } catch (Throwable unused4) {
            return null;
        }
    }

    protected AbstractC3384a(HashSet<String> hashSet, HashMap<String, HashSet<String>> hashMap) {
        this.f10730a = hashSet;
        this.f10731b = hashMap;
    }

    /* renamed from: a */
    public final boolean mo13635a(String str, String str2) {
        HashMap<String, HashSet<String>> hashMap;
        HashSet<String> hashSet;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        HashSet<String> hashSet2 = this.f10730a;
        if (hashSet2 != null && hashSet2.size() > 0) {
            if (mo13634a(str)) {
                return false;
            }
            if (TextUtils.isEmpty(str2)) {
                return true;
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str2);
            } catch (JSONException unused) {
            }
            if (jSONObject != null && (hashMap = this.f10731b) != null && hashMap.size() > 0 && this.f10731b.containsKey(str) && (hashSet = this.f10731b.get(str)) != null && hashSet.size() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    if (mo13636a(hashSet, keys.next())) {
                        try {
                            keys.remove();
                        } catch (Throwable unused2) {
                        }
                    }
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public static final AbstractC3384a m14148a(Context context, JSONObject jSONObject) {
        AbstractC3384a aVar;
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("sp_filter_name", 0).edit();
            edit.clear().commit();
            if (jSONObject == null) {
                return null;
            }
            if (!jSONObject.has("event_list")) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("event_list");
            if (optJSONObject == null) {
                return null;
            }
            int optInt = optJSONObject.optInt("is_block", 0);
            edit.putInt("is_block", optInt);
            HashSet hashSet = new HashSet();
            JSONArray optJSONArray = optJSONObject.optJSONArray("events");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        hashSet.add(optString);
                    }
                }
            }
            if (hashSet.size() > 0) {
                edit.putStringSet("events", hashSet);
            }
            HashMap hashMap = new HashMap();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("params");
            if (optJSONObject2 != null) {
                Iterator<String> keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!TextUtils.isEmpty(next)) {
                        HashSet hashSet2 = new HashSet();
                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray(next);
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                String optString2 = optJSONArray2.optString(i2);
                                if (!TextUtils.isEmpty(optString2)) {
                                    hashSet2.add(optString2);
                                }
                            }
                        }
                        if (hashSet2.size() > 0) {
                            hashMap.put(next, hashSet2);
                        }
                    }
                }
            }
            if (hashMap.size() > 0) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    edit.putStringSet((String) entry.getKey(), (Set) entry.getValue());
                }
            }
            edit.commit();
            if (optInt > 0) {
                aVar = new C3386c(hashSet, hashMap);
            } else {
                aVar = new C3385b(hashSet, hashMap);
            }
            return aVar;
        } catch (Throwable unused) {
            return null;
        }
    }
}
