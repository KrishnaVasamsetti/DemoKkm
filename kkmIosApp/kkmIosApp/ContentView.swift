import SwiftUI
import kkmshared

struct ContentView: View {
    let greet = Greeting().greeting()
    let appName = Greeting().getMyAppName()
    let userDetails = UserDetails()
    @State var isSignUpActivate: Bool = false
    @State var showAlert: Bool = false
    @State var userNameText: String = ""
    @State var passwordText: String = ""
    
    func load() {
        userDetails.fetchBaseResponseModel {
            res, error in
            if let result = res {
                self.userNameText = "\(result)"
            }
        }
    }
    
    func loadFromDb() {
        let details = StudentDetails(id: 4, name: "Krishna", age: 24, college_name: "DLR college")
        let appDb = KDatabase(databaseDriverFactory: DatabaseDriverFactory())
        appDb.insertStudentItem(model: details)
                let list = appDb.getAllStudentList()
        self.userNameText = "\(list)"
    }
        
    var body: some View {
        
        let userNameTextField = TextField("Username", text:$userNameText).textFieldStyle(.roundedBorder)
            .keyboardType(.emailAddress)
        
        let passwordTextField = SecureField("Password", text:$passwordText).textFieldStyle(.roundedBorder)
            .keyboardType(.default)
            
        let loginButton = Button("Login") {
            let isValidFiedls = userDetails.isValidDetails(userName: userNameText, password: passwordText)
               isSignUpActivate = isValidFiedls
               showAlert = !isValidFiedls
        }
            .padding()
            .alert(isPresented: $showAlert, title: "Alert", message: "Invalid fields")
        
        let signUpButton = NavigationLink(destination: SignUpView()) {
            Text("Sign Up")
        }
        
        let uiStack = VStack(alignment: .center) {
            
            userNameTextField
            passwordTextField

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
                //load()
                loadFromDb()
            }
            
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

