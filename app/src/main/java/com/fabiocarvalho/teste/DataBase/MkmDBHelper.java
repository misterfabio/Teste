package com.fabiocarvalho.teste.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.database.SQLException;
import android.util.Log;


public class MkmDBHelper extends SQLiteOpenHelper
{
    final static String TAG = "### MkmDBHelper"; // LogCat
    private static String DB_PATH = "";
    private static String DB_NAME ="mkm_db"; // o nome do seu banco
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public MkmDBHelper(Context context)
    {
        super(context, DB_NAME, null, 1); // 1 = versão da base
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }


    // Inlcuído por mim
    @Override
    public void onCreate(SQLiteDatabase db) {
        //se a base não existe, copia da pasta assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            Log.e(TAG, "Vai tentar criar a base");
            try{
                //copia a base
                copyDataBase();
                Log.e(TAG, "createDatabase base criada");
            }
            catch (IOException mIOException){
                Log.e(TAG, mIOException.getMessage());
                throw new Error("Erro copiando a base");

            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() throws IOException
    {
        //se a base não existe, copia da pasta assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                //copia a base
                copyDataBase();
                Log.e(TAG, "createDatabase base criada");
            }
            catch (IOException mIOException)
            {
                throw new Error("Erro copiando a base");
            }
        }
    }

    //verifica se a base existe na pasta: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copia a base da pasta assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Abra a base de dados pra fazer consulta
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close()
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }
}