package common.utility.response;

import common.utility.requests.UpdateByIdCommandRequest;

public class UpdateByIdCommandResponse extends Response{
        public UpdateByIdCommandResponse(String error) {
            super("update_by_id", error);
        }
}
