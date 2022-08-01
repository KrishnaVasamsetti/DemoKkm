import SwiftUI
import kkmshared

struct ContentView: View {
    let greet = Greeting().greeting()
    let appName = Greeting().getMyAppName()
    
    let localTime = Greeting().getCurrentDataTime()
    let userNameKey = "USER_KEY"
    let passwordKey = "PASSWORD_KEY"
    
    let userDetails = UserDetails()
    @State var isSignUpActivate: Bool = false
    @State var showAlert: Bool = false
    @State var userNameText: String = ""
    @State var passwordText: String = ""
    
    func load() {
        let pref = KMMPreference(context:UserDefaults.standard)

        userDetails.fetchBaseResponseModel {
            res, error in
            if let result = res {
                //self.userNameText = "\(localTime) \(result)"
                let userLocal = pref.getString(key: userNameKey)
//                if let u = userLocal {
                    self.userNameText = userLocal!
//                }
                let passwordLocal = pref.getString(key: passwordKey)
                if let pwd = passwordLocal {
                    self.passwordText = pwd
                    print("pass \(pwd)")
                }
                
            }
        }
    }
    
    func loadFromDb() {
        userDetails.fetchStudentListAsModel { res, error in
            if let studetnDetails = res {
            let details = StudentDetails(id: 4, name: "Krishna", age: 24, college_name: "DLR college")
            let appDb = KDatabase(databaseDriverFactory: DatabaseDriverFactory())
            appDb.insertStudentItem(model: studetnDetails)
                    let list = appDb.getAllStudentList()
            self.userNameText = "\(localTime) \(list)"
            }
        }
        
    }
        
    var body: some View {
        
        let userNameTextField = TextField("Username", text:$userNameText).textFieldStyle(.roundedBorder)
            .keyboardType(.emailAddress)
        
        let passwordTextField = SecureField("Password", text:$passwordText).textFieldStyle(.roundedBorder)
            .keyboardType(.default)
            
        let loginButton = Button("Login") {
            let pref = KMMPreference(context:UserDefaults.standard)

            let isValidFiedls = userDetails.isValidDetails(userName: userNameText, password: passwordText)
               isSignUpActivate = isValidFiedls
               showAlert = !isValidFiedls
            if(isValidFiedls) {
                pref.putString(key: userNameKey, value: userNameText)
                pref.putString(key: passwordKey, value: passwordText)
                print("Updated here")
            }
        }
            .padding()
            .alert(isPresented: $showAlert, title: "Alert", message: "Invalid fields")
        
        let signUpButton = NavigationLink(destination: SignUpView()) {
            Text("Sign Up")
        }
        
        let uiStack = VStack(alignment: .center) {
            
            userNameTextField
            passwordTextField3

            NavigationLink(destination: StudentListScreen(), isActive: $isSignUpActivate) {
                EmptyView()
            }
            
            loginButton
            signUpButton

        }
        
        NavigationView() {
            
            ZStack(alignment: .top) {
                uiStack //content
            }
            .padding()
            .onAppear() {
                load()
//                loadFromDb()
            }
            
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

